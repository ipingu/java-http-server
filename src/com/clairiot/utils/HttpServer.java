package com.clairiot.utils;

import java.io.File;
import java.io.IOException;

import org.jibble.simplewebserver.SimpleWebServer;


/**
 * Run a web server at a specific port, exposing a specific path.
 * 
 * @author Erik Clairiot
 *
 */
public class HttpServer {
	
	/**
	 * First argument is the path to expose.
	 * Second argument is the port to use. If no argument is given, the default port is 80.
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		if (args.length == 0) {
			System.err.println("At least one argument is necessary (path to expose)");
			System.err.println("Usage : HttpServer [path] [port:80]");
			System.exit(-1);
		}
		
		File path = new File(args[0]);
		if (path.exists() && path.isDirectory() && path.canRead()) {
			int port = 80;
			if (args.length == 2) {
				try {
					port = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.err.println("The port must be an integer. Default port 80 is used");
				}
			}
			
			try {
				System.out.println("Server launched on port " + port + " and exposes " + path.getAbsolutePath());
				SimpleWebServer server = new SimpleWebServer(path, port);
			} catch (IOException e) {
				System.err.println("Something wrong happen during server launch");
				e.printStackTrace();
			}
		} else {
			System.err.println("Path " + args[0] + " must be an existing and readable directory");
		}
		
	}

}
