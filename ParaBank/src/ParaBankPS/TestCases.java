package ParaBankPS;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ParaBankPS.Constants;

public class TestCases extends BasePB {

	public String sheetname = "Transfer Funds";

	public TestCases() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeTest
	public void setUp() throws InterruptedException {
		initialization();
	}

	@DataProvider
	public Object[][] getTransferFundsData() {
		Object[][] TFdata = Constants.getTestData(sheetname);
		System.out.println(TFdata);
		return TFdata;
	}

	@Test(priority = 1, dataProvider = "getTransferFundsData")
	public void transferFunds(String Amt, String From_Acct, String To_Acct) throws InterruptedException {

		driver.findElement(By.linkText("Transfer Funds")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("amount")).sendKeys(Amt);

		{
			Select fromAccount = new Select(driver.findElement(By.id("fromAccountId")));
			fromAccount.selectByValue(From_Acct);
		}

		{
			Select toAccount = new Select(driver.findElement(By.id("toAccountId")));
			toAccount.selectByValue(To_Acct);
		}

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		if (driver.getPageSource().contains("Transfer Complete!")) {
			System.out.println("Amount Transfer Successful from: " + From_Acct + " to: " + To_Acct);
		} else {
			System.out.println("Bill Payment unsuccessful from: " + From_Acct + " to: " + To_Acct);
		}

	}

	@DataProvider
	public Object[][] getPayBillsData() {
		sheetname = "Pay Bills";
		Object[][] PBdata = Constants.getTestData(sheetname);
		System.out.println(PBdata);
		return PBdata;
	}

	@Test(priority = 2, dataProvider = "getPayBillsData")
	public void billPayment(String PayeeName, String Address, String city, String state, String zipCode, String phone,
			String account_no, String cnf_acct, String amt, String fromacct) {

		driver.findElement(By.linkText("Bill Pay")).click();

		driver.findElement(By.name("payee.name")).sendKeys(PayeeName);
		driver.findElement(By.name("payee.address.street")).sendKeys(Address);
		driver.findElement(By.name("payee.address.city")).sendKeys(city);
		driver.findElement(By.name("payee.address.state")).sendKeys(state);
		driver.findElement(By.name("payee.address.zipCode")).sendKeys(zipCode);
		driver.findElement(By.name("payee.phoneNumber")).sendKeys(phone);
		driver.findElement(By.name("payee.accountNumber")).sendKeys(account_no);
		driver.findElement(By.name("verifyAccount")).sendKeys(cnf_acct);
		driver.findElement(By.name("amount")).sendKeys(amt);

		driver.findElement(By.name("fromAccountId")).click();
		{
			Select frm_acct = new Select(driver.findElement(By.name("fromAccountId")));
			frm_acct.selectByValue(fromacct);
		}

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		if (driver.getPageSource().contains("Bill Payment Complete")) {
			System.out.println("Bill Payment successful from: " + account_no + " to: " + PayeeName);
		} else {
			System.out.println("Bill Payment unsuccessful from: " + account_no + " to: " + PayeeName);
		}

	}

	@DataProvider
	public Object[][] getUpdateInfoData() {
		sheetname = "Update Contact Info";
		Object[][] UCIdata = Constants.getTestData(sheetname);
		System.out.println(UCIdata);
		return UCIdata;
	}

	@Test(priority = 3, dataProvider = "getUpdateInfoData")
	public void updateContact(String first_Name, String last_Name, String address, String city, String state,
			String zip, String phone) {

		driver.findElement(By.linkText("Update Contact Info")).click();
		driver.findElement(By.id("customer.firstName")).sendKeys(first_Name);
		driver.findElement(By.id("customer.lastName")).sendKeys(last_Name);
		driver.findElement(By.id("customer.address.street")).sendKeys(address);
		driver.findElement(By.id("customer.address.city")).sendKeys(city);
		driver.findElement(By.id("customer.address.state")).sendKeys(state);
		driver.findElement(By.id("customer.address.zipCode")).sendKeys(zip);
		driver.findElement(By.id("customer.phoneNumber")).sendKeys(phone);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		if (driver.getPageSource().contains("Profile Updated")) {
			System.out.println("Details have been updated to the system");
		} else {
			System.out.println("Details haven't been updated to the system");
		}
	}

	@DataProvider
	public Object[][] getOpenAccountData() {
		sheetname = "Open Account";
		Object[][] PFCdata = Constants.getTestData(sheetname);
		System.out.println(PFCdata);
		return PFCdata;
	}

	@Test(priority = 4, dataProvider = "getOpenAccountData")
	public void openNewAccount(String act_type, String act_no) {

		driver.findElement(By.linkText("Open New Account")).click();

		{
			Select acct_type = new Select(driver.findElement(By.id("type")));
			acct_type.selectByVisibleText(act_type.toUpperCase());
		}

		{
			Select acct_no = new Select(driver.findElement(By.id("fromAccountId")));
			acct_no.selectByVisibleText(act_no);
		}

		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@DataProvider
	public Object[][] getTransactionData() {
		sheetname = "Find a Transaction";
		Object[][] FTdata = Constants.getTestData(sheetname);
		System.out.println(FTdata);
		return FTdata;
	}

	@Test(priority = 5, dataProvider = "getTransactionData")
	public void findTransaction(String acct_no, String trans_id, String trans_date, String start_date, String end_date,
			String amount) throws InterruptedException {

		if (trans_id == null) {
			trans_id = "";
		}
		if (trans_date == null) {
			trans_date = "";
		}
		if (start_date == null) {
			start_date = "";
		}
		if (end_date == null) {
			end_date = "";
		}
		if (amount == null) {
			amount = "";
		}

		driver.findElement(By.linkText("Find Transactions")).click();
		Thread.sleep(2000);

		if (acct_no != null) {
			Select account = new Select(driver.findElement(By.id("accountId")));
			account.selectByValue(acct_no);
			if (trans_id != "") {
				driver.findElement(By.id("criteria.transactionId")).sendKeys(trans_id);
				// driver.findElement(By.xpath("//button[@type='submit' and
				// @ng-click='criteria.searchType = 'ID'']")).submit();
				driver.findElement(By.cssSelector("div:nth-child(5) > .button")).click();
			} else if (trans_date != "") {
				driver.findElement(By.id("criteria.onDate")).sendKeys(trans_date);
				// driver.findElement(By.xpath("//button[@type='submit' and
				// @ng-click='criteria.searchType = 'DATE'']")).submit();
				driver.findElement(By.cssSelector("div:nth-child(9) > .button")).click();
			} else if (start_date != "") {
				driver.findElement(By.id("criteria.fromDate")).sendKeys(start_date);
				if (end_date != "") {
					driver.findElement(By.id("criteria.toDate")).sendKeys(end_date);
					// driver.findElement(By.xpath("//button[@type='submit' and
					// @ng-click='criteria.searchType = 'DATE_RANGE'']")).submit();
					driver.findElement(By.cssSelector("div:nth-child(13) > .button")).click();
				} else {
					driver.findElement(By.id("criteria.toDate")).sendKeys("");
					System.out.println("Please enter both start date and end dates");
				}
			} else if (amount != "") {
				driver.findElement(By.id("criteria.amount")).sendKeys(amount);
				// driver.findElement(By.xpath("//button[@type='submit' and
				// @ng-click='criteria.searchType = 'AMOUNT'']")).submit();
				driver.findElement(By.cssSelector("div:nth-child(17) > .button")).click();
			} else {
				System.out.println("Please select any value to look for the transactions");
			}
		} else {
			System.out.println("Please select an account number");
		}

		// Printing the list of transactions found
		List<WebElement> transactions = driver
				.findElements(By.xpath("//div[@class='ng-scope']/table[@id='transactionTable']/tbody/tr"));
		int no_of_transactions = transactions.size();
		System.out.println("number of transactions found: " + no_of_transactions);

		if (no_of_transactions != 0) {
			// Printing data
			// Get number of rows In table.
			int Row_count = transactions.size();
			System.out.println("Number Of Rows = " + Row_count);

			// Get number of columns In table.
			int Col_count = driver
					.findElements(By.xpath("//div[@class='ng-scope']/table[@id='transactionTable']/thead/tr/th"))
					.size();
			System.out.println("Number Of Columns = " + Col_count);

			for (int k = 1; k <= Col_count; k++) {
				String Table_Head = driver
						.findElement(By.xpath(
								"//div[@class='ng-scope']" + "/table[@id='transactionTable']/thead/tr/th[" + k + "]"))
						.getText();
				System.out.print(Table_Head + "||");
			}
			System.out.print("\n");
			// divided xpath In three parts to pass Row_count and Col_count values.
			String xpath_1 = "//div[@class='ng-scope']/table[@id='transactionTable']/tbody/tr[";
			String xpath_2 = "]/td[";
			String xpath_3 = "]";

			for (int i = 1; i <= Row_count; i++) {
				for (int j = 1; j <= Col_count; j++) {
					String final_xpath = xpath_1 + i + xpath_2 + j + xpath_3;
					String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
					System.out.print(Table_data + "||");
				}
				System.out.println("\n");
			}

		}

	}

	@AfterTest
	public void tearDown() {
		driver.findElement(By.linkText("Log Out")).click();
		driver.quit();
	}

}
