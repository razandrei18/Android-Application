package com.example.mytown;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailActivity extends AppCompatActivity {
    EditText emailSubiect, emailMesaj;
    Button btnTrimitere;
    String sEmail, sPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        final Intent dataMail= getIntent();

        emailSubiect = findViewById(R.id.mail_subject);
        emailMesaj = findViewById(R.id.mail_mesaj);
        btnTrimitere = findViewById(R.id.mail_trimitereBtn);

        //Credentialele celui care trimite

        sEmail = "razvanioan.andrei22@gmail.com";
        sPassword = "razvan18@!";

        btnTrimitere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session= Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail, sPassword);
                    }
                });

                try {
                    Message message=new MimeMessage(session);
                    message.setFrom(new InternetAddress(sEmail));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dataMail.getStringExtra("email_contact")));
                    message.setSubject(emailSubiect.getText().toString().trim());
                    message.setText(emailMesaj.getText().toString().trim());

                    new SendMail().execute(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class SendMail  extends AsyncTask<Message, String, String> {
        //initializarea progress dialog ului
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Crearea si afisarea progress dialog ului

            progressDialog = ProgressDialog.show(MailActivity.this, "Trimitere", "Rezervare in curs de trimitere...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Succes";
            } catch (MessagingException e) {

                e.printStackTrace();
                return "Eroare la trimitere";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Ascunderea progress dialog-ului
            progressDialog.dismiss();
            if (s.equals("Succes")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MailActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Succes</font>"));
                builder.setMessage("Mesaj trimis cu succes!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        emailSubiect.setText("");
                        emailMesaj.setText("");
                    }
                });

                builder.show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Ceva nu a functionat!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

