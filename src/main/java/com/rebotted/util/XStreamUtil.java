package com.rebotted.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.rebotted.game.npcs.NPCDefinition;
import com.thoughtworks.xstream.XStream;

public class XStreamUtil {
	
	private static XStreamUtil instance = new XStreamUtil();
	private static final XStream xStream = new XStream();
	
	public static XStreamUtil getInstance() {
		return instance;
	}
        
    static {
		xStream.alias("npcDefinition", NPCDefinition.class);
	}

	public static XStream getXStream() {
		return xStream;
	}
	
    public static void writeXML(Object object, File file) throws IOException {
        try (FileOutputStream out = new FileOutputStream(file)) {
            xStream.toXML(object, out);
            out.flush();
        }
    }

}
