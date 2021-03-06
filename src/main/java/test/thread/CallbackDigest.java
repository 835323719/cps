/**
 * 
 */
package test.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author admin
 *
 */
public class CallbackDigest implements Runnable{
	private File inputFile;         //目标文件 

    public CallbackDigest(File input) { 
        this.inputFile = input; 
    } 

    public void run() { 
        try { 
            FileInputStream in = new FileInputStream(inputFile); 
            MessageDigest sha = MessageDigest.getInstance("MD5"); 
            DigestInputStream din = new DigestInputStream(in, sha); 
            int b; 
            while ((b = din.read()) != -1) ; 
            din.close(); 
            byte[] digest = sha.digest();   //摘要码 
            //完成后，回调主线程静态方法，将文件名-摘要码结果传递给住线程  
            CallbackDigestUserInterface.receiveDigest(digest, inputFile.getName()); 
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
}
