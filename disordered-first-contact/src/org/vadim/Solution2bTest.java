package org.vadim;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Solution2bTest {
	private InputStream sysIn;
	private PrintStream sysOut;

	@Before
	public void before() {
		sysIn = System.in;
		sysOut = System.out;
	}

	@After
	public void after() {
		System.setIn(sysIn);
		System.setOut(sysOut);
	}

	@Test
	public void testMain1() {
		StringBuilder TEXT = new StringBuilder(" rius lorem. Duis risus nunc, condimentum at metun lacinia id. Pellentebortis. Suspendttis sed , maxis ornare nipulvinar. In v aliquam erat maximus bibenetus neque, tempus lovarius ipsnare vel. Donec , vitae sx enim. Sed vitaes sed nei ipFusces t. e at sum. Alt nibhgittidisse eu eteger id cursumque vel dui et libs.Maecenash. Suspendisse tristiqueeu condcondimentum atec orDui sitipsuorLem m dolteger quismus eget i ssim lacuss. Suspum feron arcu idvinar id eula elit in effiuspenlor. in blandem solm ne i psuc lorlicitudit ut acSIn luctus vcitur vae pulat arcu ferment maximus. Integerendisse hendrim. Inmentum nibh non dum.  amet, tur adlit. Fusceci pretium iacsi ut felibm neque, quis dignis orligsx nec sagi aliquam do maximuaodo nulla. isi quis, iquam esdu, npretium comMauris as. Ins elitque a mattittis. Morbi volutpat eroegestas irit vel ante ac dignisss nes scing elitconsecteoripi. Quisque msagiel puruuli mollis n enim est, ac bibendumissmentum. Ut dictum mi vel luctus rhoncus.tempor id.");
		int rounds = 1;
		
		ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bufOut));

		char dig = 'a';
		for (int i = 0; i < 1000; i++) {
			TEXT.append(dig++);
			if (dig > 'Z') dig = 'a';
			System.setIn(new ByteArrayInputStream(("-" + rounds + '\n' + TEXT.toString() + '\n').getBytes()));

			bufOut.reset();
			Solution2.main(new String[0]);
			String encText = bufOut.toString();
//			System.err.println("enc=" + encText);

			/**
			 * Decode
			 */
			bufOut.reset();
			System.setIn(new ByteArrayInputStream(("" + rounds + '\n' + encText + '\n').getBytes()));

			Solution2.main(new String[0]);
			String decText = bufOut.toString();
			
			if((TEXT.toString() + '\n').compareTo(decText) != 0) {
				System.err.println("ERROR\n" + TEXT + "\n" + decText);
			}
			assertEquals(TEXT.toString() + '\n', decText);
		}
	}
}
