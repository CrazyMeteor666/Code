package costom;


public boolean IsImageVisible(WebDriver driver,WebElement image)
{  
    Boolean imageLoaded1 = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", image);   
    if (!imageLoaded1)   
    {   
        MyLog.logger.info("----Image is not present.-----");   
        return false;  
    }   
    else   
    {   
        MyLog.logger.info("----image is visible.----");   
    }  
    return true;  
} 