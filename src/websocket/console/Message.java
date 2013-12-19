/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package websocket.console;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.atomic.AtomicInteger;

import websocket.console.SocketAnnotation.MessageType;

/**
 * A message that represents a drawing action.
 * Note that we use primitive types instead of Point, Color etc.
 * to reduce object allocation.<br><br>
 *
 * TODO: But a Color objects needs to be created anyway for drawing this
 * onto a Graphics2D object, so this probably does not save much.
 */
public final class Message {
	private static final AtomicInteger messageIds = new AtomicInteger(0);
	
	private long iDmessage;
	private int auteur=-1;
    private MessageType type;
    private String message;
    private int destination=-1;

    
	public long getiDmessage() {
		return iDmessage;
	}
	public void setiDmessage(long iDmessage) {
		this.iDmessage = iDmessage;
	}
	public int getAuteur() {
		return auteur;
	}
	public void setAuteur(int auteur) {
		this.auteur = auteur;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public Message() {
		super();
		iDmessage=messageIds.getAndIncrement();
		// TODO Auto-generated constructor stub
	}
	public Message(MessageType type, String message) {

        this.type = type;
        this.message = message;
        iDmessage=messageIds.getAndIncrement();
    }
	public Message(MessageType type, String message,int auteur) {

        this.type = type;
        this.message = message;
        this.auteur=auteur;
        iDmessage=messageIds.getAndIncrement();
    }


	/**
     * Converts this message into a String representation that
     * can be sent over WebSocket.<br>
     * 
     */
    @Override
    public String toString() {

        //return auteur + "," + type.toString() + "," + message+ "," + destination;
    	
    	return message;
    }

    public Message parseFromString(String str)
            throws ParseException {

    	MessageType type;
        String message;
        int destination=-1;

        try {
            String[] elements = str.split(",");
            if(elements.length==1)
            {
            	type=MessageType.Error;
            	message=elements[0];
            }
            else if(elements.length==2)
            {
            	 type = MessageType.getMessageType(Integer.parseInt(elements[0]));
            	 message = elements[1];
            }
            else if(elements.length==3)
            {
            	type = MessageType.getMessageType(Integer.parseInt(elements[0]));
            	message = elements[1];
                destination=Integer.parseInt(elements[2]);
            }
            else {
            	type=MessageType.Error;
            	message="parseFromString Message Error";
            }
           
        } catch (RuntimeException ex) {
            return new Message(MessageType.Error, ex.getMessage());
        }

        Message m = new Message(type, message);
        m.setDestination(destination);

        return m;
    }

    public static class ParseException extends Exception {
        private static final long serialVersionUID = -6651972769789842960L;

        public ParseException(Throwable root) {
            super(root);
        }

        public ParseException(String message) {
            super(message);
        }
    }



}