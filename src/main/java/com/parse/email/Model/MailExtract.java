package com.parse.email.Model;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.*;
import javax.mail.search.AndTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;

public class MailExtract {

	public static void extractEmail(String EmailAddress, String Password) throws MessagingException, IOException {
		System.out.println("Inside mail extract");
		IMAPFolder folder = null;
		Store store = null;
		String subject = null;
		Flag flag = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");

			Session session = Session.getDefaultInstance(props, null);

			store = session.getStore("imaps");
			store.connect("imap.gmail.com",EmailAddress,Password );

			folder = (IMAPFolder) store.getFolder("inbox"); // This doesn't work for other email account
			// folder = (IMAPFolder) store.getFolder("inbox"); This works for both email
			// account
			Flags seen = new Flags(Flags.Flag.SEEN);
			FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
//            return emailFolder.search(unseenFlagTerm);
//            FlagTerm fromMail = new FlagTerm(new InternetAddress("padma.joseph@iiitb.ac.in"),true);

			if (!folder.isOpen())
				folder.open(Folder.READ_WRITE);
//            Message[] messages = folder.getMessages();
			SearchTerm searchterm1 = new FromTerm(new InternetAddress("himavyas14@gmail.com"));
			SearchTerm searchterm2 = new AndTerm(searchterm1, unseenFlagTerm);
//            Message[] messages = folder.search(unseenFlagTerm);
			Message[] messages = folder.search(searchterm2);
			System.out.println("No of Messages : " + folder.getMessageCount());
			System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
			System.out.println(messages.length);
			for (int i = 0; i < messages.length; i++) {

//              System.out.println("*****************************************************************************");
//              System.out.println("MESSAGE " + (i + 1) + ":");
				Message msg = messages[i];
				// System.out.println(msg.getMessageNumber());
				// Object String;
				// System.out.println(folder.getUID(msg)
				String contentType = msg.getContentType();
				if (contentType.contains("multipart")) {
					Multipart multiPart = (Multipart) msg.getContent();

					for (int i1 = 0; i1 < multiPart.getCount(); i1++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i1);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// this part is attachment
							// code to save attachment...
							String destFilePath = "/home/hima/Downloads/ELK_NEW/ELK/input/" + part.getFileName();
							File f = new File(destFilePath);
							FileOutputStream output = new FileOutputStream(f);
//              	    	OutputStream output = new Out

							InputStream input = part.getInputStream();

//              	    	byte[] buffer = new byte[input.available()];
							byte[] buffer = new byte[4096];

							int byteRead;
//              	    	 input.read(buffer);
//              	    	 output.write(buffer);
							while ((byteRead = input.read(buffer)) != -1) {
								output.write(buffer, 0, byteRead);
								System.out.print("a");
							}
							output.close();
							System.out.println(buffer);

						}
					}
				}
				subject = msg.getSubject();
				//
				System.out.println("Subject: " + subject);
				System.out.println("From: " + msg.getFrom()[0]);
				System.out.println("To: " + msg.getAllRecipients()[0]);
				System.out.println("Date: " + msg.getReceivedDate());
				System.out.println("Size: " + msg.getSize());
				System.out.println(msg.getFlags());
				System.out.println("Body: \n" + msg.getContent());
				System.out.println(msg.getContentType());

			}
		} finally {
			if (folder != null && folder.isOpen()) {
				folder.close(true);
			}
			if (store != null) {
				store.close();
			}
		}

	}
}
