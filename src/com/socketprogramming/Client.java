package com.socketprogramming;

// A Java Program for server

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException{
		// initialize socket and input streams
		Socket s1 = null;
		String line = null;    // string to read message from input
		DataInputStream is = null;
		DataInputStream br = null;
		PrintWriter os = null;
		
		try {
			s1 = new Socket("localhost",7050);
			br = new DataInputStream(System.in);   
			is = new DataInputStream(s1.getInputStream());
			os = new PrintWriter(s1.getOutputStream());  // sends output to the socket
		}catch(IOException e) {
			System.out.println("IO Exception");
		}
		
		System.out.println("Enter Data to Echo Server(Enter Quit to End)");
		String response = null;
		
		try {
			line = br.readLine();
			 // keep reading until "Quit" is input
			while(line.compareTo("Quit")!=0) {
				os.println(line);
				os.flush();
				response = is.readLine();
				System.out.println("Server Response : "+response);
				line = br.readLine();
			}
			// close connection
			is.close();
			os.close();
			br.close();
			s1.close();
			System.out.println("Connection Closed...");
		}catch(Exception e) {
			System.out.println("Socket Read Error!!!");
		}
	
	}
}


