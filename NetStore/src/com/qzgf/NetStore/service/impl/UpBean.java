package com.qzgf.NetStore.service.impl;

import java.io.*; 

import javax.servlet.http.HttpServletRequest; 

import javax.servlet.http.HttpServletResponse; 

import javax.servlet.ServletInputStream; 



public class UpBean { 



public void doUpload(HttpServletRequest request) throws 

IOException { 

PrintWriter pw = new PrintWriter( 

new BufferedWriter(new FileWriter("test.txt"))); 

ServletInputStream in = request.getInputStream(); 



int i = in.read(); 

while (i != -1) { 

pw.print((char) i); 

i = in.read(); 

} 

pw.close(); 

} 

} 

