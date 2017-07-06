package ita.Q4_P2_CES29;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import ita.q4p2ces29.VulnerableClass;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

public class VulnerableClassTest {

	@Test
	public void InvalidFilenameTest() {
		VulnerableClass v = new VulnerableClass();
		String filename = "{  nocivo;  }";
		
		try {
			v.vulnerableMethod(filename);
			fail();
		}
		catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	@Rule
	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void InvalidScannerOperation() {
		systemInMock.provideLines("OPERAÇÃO INVÁLIDA");
		VulnerableClass v = new VulnerableClass();
		String filename = "permitido";
		
		try {
			try {
				v.vulnerableMethod(filename);
				fail();
			}
			catch(IllegalArgumentException e) {
				//e.printStackTrace();
			}
		}
		catch(IOException f) {
			fail();
		}
	}
	
	@Test
	public void OperationReadFromUnexistentFile() {
		systemInMock.provideLines("R");
		VulnerableClass v = new VulnerableClass();
		String filename = "read";
		
		try {
			v.vulnerableMethod(filename);
			fail();
		}
		catch(IOException f) {
			//f.printStackTrace();
		}
	}
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Test
	public void OperationReadFromFile() {
		systemInMock.provideLines("R");
		VulnerableClass v = new VulnerableClass();
		String filename = "read.txt";
		
		try {
			v.vulnerableMethod(filename);
			assertEquals("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, W para escrever em um arquivo>? Teste\r\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0", systemOutRule.getLog());
		}
		catch(IOException f) {
			fail();
			//f.printStackTrace();
		}
	}
	
	@Test
	public void OperationReadFromOver100CaractersFile() {
		systemInMock.provideLines("R");
		VulnerableClass v = new VulnerableClass();
		String filename = "readOver100.txt";
		
		try {
			v.vulnerableMethod(filename);
			assertEquals("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, W para escrever em um arquivo>? Essa linha possui mais de 100 caracteres! Essa linha possui mais de 100 caracteres! Essa linha possui mais de 100 caracteres!\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0", systemOutRule.getLog());
		}
		catch(IOException f) {
			fail();
			//f.printStackTrace();
		}
	}

	@Test
	public void OperationWriteToFileThenRead() {
		systemInMock.provideLines("W", "escrevi algo", "R");
		VulnerableClass v = new VulnerableClass();
		String filename = "write.txt";
		
		try {
			//writes
			v.vulnerableMethod(filename);
			systemOutRule.clearLog();
			//reads
			v.vulnerableMethod(filename);
			assertEquals("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, W para escrever em um arquivo>? escrevi algo\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0",systemOutRule.getLog());
		} catch (IOException e) {
			fail();
			//e.printStackTrace();
		}
	}
	
	@Test
	public void OperationWriteOver100CaractersThenRead() {
		systemInMock.provideLines("W", "Essa linha possui mais de 100 caracteres! Essa linha possui mais de 100 caracteres! Essa linha possui mais de 100 caracteres! ", "R");
		VulnerableClass v = new VulnerableClass();
		String filename = "writeOver100.txt";
		
		try {
			//writes
			v.vulnerableMethod(filename);
			systemOutRule.clearLog();
			//reads
			v.vulnerableMethod(filename);
			assertEquals("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, W para escrever em um arquivo>? Essa linha possui mais de 100 caracteres! Essa linha possui mais de 100 caracteres! Essa linha possu\n\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0",systemOutRule.getLog());
		} catch (IOException e) {
			fail();
			//e.printStackTrace();
		}
	}
	
}
