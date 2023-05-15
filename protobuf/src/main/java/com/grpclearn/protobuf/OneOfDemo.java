package com.grpclearn.protobuf;

import com.grpclelarn.models.Credentials;
import com.grpclelarn.models.EmailCredentials;
import com.grpclelarn.models.PhoneOtp;

public class OneOfDemo {
	public static void main(String[] args) {
		EmailCredentials emailCred = EmailCredentials.newBuilder().setEmail("glen@gmail.com").setPassword("password").build();
		PhoneOtp phoneOtp = PhoneOtp.newBuilder().setNumber(1234567890).setCode(456).build();
		Credentials cred = Credentials.newBuilder().setPhoneMode(phoneOtp).setEmailMode(emailCred).build();
		login(cred);
	}
	
	private static void login(Credentials credentials) {
		switch (credentials.getModeCase()) {
		case EMAILMODE:{
			System.out.println(credentials.getEmailMode());
			break;
		}
		case PHONEMODE:{
			System.out.println(credentials.getPhoneMode());
			break;
		}

		default:
			break;
		}
	}
}
