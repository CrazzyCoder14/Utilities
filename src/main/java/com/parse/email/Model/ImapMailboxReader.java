package com.parse.email.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class ImapMailboxReader {
	private static SortedMap<String, Address> collectedAddresses = new TreeMap<String, Address>();

	/* filename */
	private static String FILENAME = "addresses.txt";

	/* login data */
	private static String USER = "himavyas14@gmail.com";
	private static String PASSWORD = "c0c0CoL@();;";
	private static String STORE = "imap.gmail.com";
	private static String FOLDER = "[Gmail]/All Mail";

	/* select addresses to extract */
	private static Boolean GETFROM = true;
	private static Boolean GETTO = true;
	private static Boolean GETCC = false;
	private static Boolean GETBCC = false;

	/* select addresses to filter */
	private static Boolean FILTER_FROM = true;
	private static Boolean FILTER_TO = false;

	/* filter addresses to extract */
	private static String addressFilter[] = { "noreply@", "no-reply@", "no.reply@", "donotreply@", "do_not_reply@",
			"webmaster@", "Gaming", "Fiesta", "Replica",  "Watches", "Cialis"
			/* your filters here ... */
	};

	public static void main(String args[]) {

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect(STORE, USER, PASSWORD);

			// Choose folder to work in
			Folder myfolder = store.getFolder(FOLDER);
			myfolder.open(Folder.READ_ONLY);

			Message messages[] = myfolder.getMessages();
			double percent = messages.length / 100;

			System.out.print(".");
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				if (i >= percent) {
					System.out.print(".");
					percent += percent;
				}

				if (GETFROM) {
					Address fromAddresses[] = message.getFrom();
					addAddresses(fromAddresses, FILTER_FROM);
				}

				if (GETTO) {
					Address[] toAddresses = message.getRecipients(Message.RecipientType.TO);
					if (toAddresses != null)
						addAddresses(toAddresses, FILTER_TO);
				}

				if (GETCC) {
					Address[] ccAddresses = message.getRecipients(Message.RecipientType.CC);
					if (ccAddresses != null)
						addAddresses(ccAddresses);
				}

				if (GETBCC) {
					Address[] bccAddresses = message.getRecipients(Message.RecipientType.BCC);
					if (bccAddresses != null)
						addAddresses(bccAddresses);
				}

			}
			System.out.println();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}

		System.out.println("Writing collected addresses to file.");

		try {
			// Create file
			FileWriter fstream = new FileWriter(FILENAME);
			BufferedWriter out = new BufferedWriter(fstream);
			// Write to file
			for (Address address : collectedAddresses.values()) {
				if (address != null) {
					out.write(address.toString());
					out.newLine();
				}
			}
			// Close the output stream
			out.close();
		} catch (Exception e) {
			// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	private static Address filter(Address address) {
		String addressString = address.toString();
		for (String filter : addressFilter) {
			if (addressString.contains(filter)) {
				return null;
			}
		}
		return address;
	}

	private static String cleanAddress(String address) {
		if (address.contains("<"))
			address = address.substring(address.lastIndexOf("<") + 1, address.indexOf(">"));
		return address.toLowerCase();
	}

	private static void addAddresses(Address[] addresses) {
		addAddresses(addresses, false);
	}

	private static void addAddresses(Address[] addresses, Boolean filter) {
		for (Address address : addresses) {
			if (filter)
				address = filter(address);
			if (address == null)
				continue;
			else
				collectedAddresses.put(cleanAddress(address.toString()), address);
		}
	}
}
