package com.cg.payment.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;
import com.cg.payment.service.IPaymentService;
import com.cg.payment.service.PaymentService;

public class PaymentTest {
	private IPaymentService service;

	@Before
	public void init() {
		service = new PaymentService();
	}

	@Test
	public void testCreateAccountForMobile() {
		Payment ra = new Payment();
		ra.setMobileNo("1234");
		ra.setName("Santosh");
		ra.setEmail("santu247908@gmail.com");
		ra.setBalance(5000.0);
		try {
			service.createAccount(ra);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	@Test
	public void testCreateAccountForName() {
		Payment ra = new Payment();
		ra.setMobileNo("9848275672");
		ra.setName("santu152670");
		ra.setEmail("subha@gmail.com");
		ra.setBalance(6000.0);
		try {
			service.createAccount(ra);
		} catch (PaymentException e) {
			assertEquals("Name should start with capital letter and should contain only alphabets", e.getMessage());
		}
	}

	@Test
	public void testCreateAccountForNameIsEmpty() {
		Payment ra = new Payment();
		ra.setMobileNo("9848216090");
		ra.setName("");
		ra.setEmail("thammu@gmail.com");
		ra.setBalance(10000.0);
		try {
			service.createAccount(ra);
		} catch (PaymentException e) {
			assertEquals("Name cannot be empty", e.getMessage());
		}
	}

	@Test
	public void testCreateAccountForEmailId() {
		Payment ra = new Payment();
		ra.setMobileNo("8985102604");
		ra.setName("Navya");
		ra.setEmail("ushasainavyasri5031@@23gmail.com");
		ra.setBalance(7000.0);
		try {
			service.createAccount(ra);
		} catch (PaymentException e) {
			assertEquals("Enter valid emailid", e.getMessage());
		}
	}

	@Test
	public void testCreateAccount() {
		Payment ra = new Payment();
		ra.setMobileNo("9440275672");
		ra.setName("Santosh");
		ra.setEmail("santu247908@gmail.com");
		ra.setBalance(5000.0);

		try {
			String s=service.createAccount(ra);
			assertNotNull(s);
		} catch (PaymentException e) {
		}

	}

	@Test
	public void testShowBalanceForMobileNo() {
		try {
			service.showBalance("95059");
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}


	/*@Test
	public void testShowBalanceForMobileNoDoesNotExist() {
		try {
			service.showBalance("9505928505");
		} catch (PaymentException e) {
			assertEquals("The mobile number does not exist",e.getMessage());
		}
	}*/

	/*@Test
	public void testShowBalanceForName() {
		Payment ra=new Payment();
		ra.setMobileNo("9848275682");
		ra.setName("Santu");
		try {
			service.showBalance(ra.getMobileNo());
			assertEquals("Santu", ra.getName());
		} catch (PaymentException e) {
			assertEquals("The mobile number does not exist",e.getMessage());
		}
	}*/

	@Test
	public void testDepositForMobileNo() {
		Payment ra=new Payment();
		ra.setMobileNo("95345");
		try {
			service.deposit(ra.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	/*@Test
	public void testDepositForMobileNoDoesNotExist() {
		Payment ra=new Payment();
		ra.setMobileNo("9505934512");
		try {
			service.deposit(ra.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("The mobile number does not exist",e.getMessage());
		}
	}*/
	@Test
	public void testDepositForDepositAmt1() {
		Payment ra=new Payment();
		ra.setMobileNo("9440275672");
		try {
			service.deposit(ra.getMobileNo(), -230);
		} catch (PaymentException e) {
			assertEquals("Deposit amount must be greater than zero",e.getMessage());
		}
	}

	@Test
	public void testDeposit() {
		Payment ra=new Payment();
		ra.setMobileNo("8985102604");
		try {
			Payment ra1=service.deposit(ra.getMobileNo(), 230);
			assertNotNull(ra1);
		} catch (PaymentException e) {

			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testWithDrawForMobileNo() {
		Payment ra=new Payment();
		ra.setMobileNo("9345");
		try {
			service.withdraw(ra.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	/*@Test
	public void testWithdrawForMobileNoDoesNotExist() {
		Payment ra=new Payment();
		ra.setMobileNo("9505934512");
		try {
			service.withdraw(ra.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("The mobile number does not exist",e.getMessage());
		}
	}*/
	@Test
	public void testWithdrawForAmt() {
		Payment ra=new Payment();
		ra.setMobileNo("8985102604");
		try {
			service.withdraw(ra.getMobileNo(), -230);
		} catch (PaymentException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}


	@Test
	public void testTransferForMobileNo() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("78945620");
		ra2.setMobileNo("1234");
		try {
			service.fundTransfer(ra.getMobileNo(),ra2.getMobileNo(), 230);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	/*@Test
	public void testTransferForMobileNoDoesNotExist() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("1234567890");
		ra2.setMobileNo("0987654321");
		try {
			service.fundTransfer(ra.getMobileNo(), ra2.getMobileNo(),  230);
		} catch (PaymentException e) {
			assertEquals("The mobile number does not exist",e.getMessage());
		}
	}*/
	@Test
	public void testTransferForAmt() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("9440275672");
		ra2.setMobileNo("8985102604");
		try {
			service.fundTransfer(ra.getMobileNo(), ra2.getMobileNo(),  -230);
		} catch (PaymentException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	@Test
	public void testTransfer() {
		Payment ra=new Payment();
		Payment ra2=new Payment();
		ra.setMobileNo("9505928555");
		ra2.setMobileNo("9848468242");
		try {
			assertTrue(service.fundTransfer(ra.getMobileNo(), ra2.getMobileNo(),  230));
		} catch (PaymentException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testPrintDetails() {
		Payment ra=new Payment();
		ra.setMobileNo("9848468242");
		try {
			Payment raa=service.printTransactionDetails(ra.getMobileNo());
			assertNotNull(raa);
		} catch (PaymentException e) {
			System.out.println(e.getMessage());
		}

	}

	/*@Test
	public void testPrinttransactionDetailsForIncorrectMobileNo() {
		Payment ra=new Payment();
		ra.setMobileNo("98492285");
		try {
			Payment acc=service.printTransactionDetails(ra.getMobileNo());
			assertNotNull(acc);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}

	}  */
	/*@Test
	public void testPrinttransactionDetailsForMObileNoDoesExistInDb() {
		Payment ac=new Payment();
		ac.setMobileNo("9875486310");
		try {
			Payment acc=service.printTransactionDetails(ac.getMobileNo());
			assertNotNull(acc);
		} catch (PaymentException e) {
			assertEquals("The mobile number does not exist",e.getMessage());
		}

	}   */
	@Test
	public void testWithDrawForMobileNoInDb() {
		Payment acc=new Payment();
		acc.setMobileNo("123456789");
		try {
			service.withdraw(acc.getMobileNo(), 600);
		} catch (PaymentException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	} 



}

