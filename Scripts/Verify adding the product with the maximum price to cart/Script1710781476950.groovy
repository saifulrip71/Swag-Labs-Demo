import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.apache.poi.ss.usermodel.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.Keys as Keys

'Navigate and Login to Swag Labs website'
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.url)
WebUI.maximizeWindow()
WebUI.setText(findTestObject('Object Repository/Page_Swag Labs_Login/textbox_user-name'), GlobalVariable.username)
WebUI.setText(findTestObject('Object Repository/Page_Swag Labs_Login/textbox_password'), GlobalVariable.password)
WebUI.click(findTestObject('Object Repository/Page_Swag Labs_Login/button_login'))


'Get the list of product prices'
List price = WebUI.findWebElements(findTestObject('Page_Swag Labs/div_price'), 30)

'initialize variables for higest price and index of it'
double maxprice = 0
int maxpriceindex = 0

'extract the price amount and get the maximum price'
for (int i = 0; i < price.size(); i++) {
	
    String textofprice = price.get(i).text
    double priceamount = Double.parseDouble(textofprice.replace('$', ''))

    if (priceamount > maxprice) {
		
        maxprice = priceamount
        maxpriceindex = (i + 1)
    }
}

'Custom element to get the Add to cart button of the higest priced product'
String xpathExpression = "(//button[text()='Add to cart'])[$maxpriceindex]"
TestObject buttonObject = new TestObject()
buttonObject.addProperty('xpath', ConditionType.EQUALS, xpathExpression)

'Click Add to cart button of the higest priced product'
WebUI.click(buttonObject)

'Going to cart page to get the product title and description'
WebUI.click(findTestObject('Page_Swag Labs/button_cart'))

String producttitle = WebUI.getText(findTestObject('Page_Swag Labs/title_product'))
String productdescription = WebUI.getText(findTestObject('Page_Swag Labs/description_product'))

'Print product title and description'
println(producttitle)
println(productdescription)

'Close the browser'
WebUI.closeBrowser()

