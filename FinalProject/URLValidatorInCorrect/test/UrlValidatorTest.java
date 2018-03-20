import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import junit.framework.TestCase;

public class UrlValidatorTest extends TestCase {

    public UrlValidatorTest(String testName) {
      super(testName);
   }

   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		
		System.out.println("Expect True for the following tests: ");
		System.out.println(urlVal.isValid("http://www.google.com"));							//returned true
		System.out.println(urlVal.isValid("http://www.google.com:65535"));						//however, returned false
		System.out.println(urlVal.isValid("http://www.google.com:65535/test1"));				//however, returned false
		System.out.println(urlVal.isValid("http://www.google.com:65535/test1/file"));			//however, returned false
		System.out.println(urlVal.isValid("http://www.google.com:65535/$23`?action=view"));		//however, returned false
		System.out.println(urlVal.isValid("http://www.google.com:80"));							//however, returned false
		System.out.println(urlVal.isValid("http://www.google.com:80/$23"));						//however, returned false
		System.out.println(urlVal.isValid("http://go.au:80"));									//however, returned false
		System.out.println(urlVal.isValid("http://go.au:0"));									//however, returned false
		System.out.println(urlVal.isValid("http://0.0.0.0:0"));									//however, returned false
		System.out.println(urlVal.isValid("http://255.255.255.255:80"));						//however, returned false
		
		System.out.println("Expect False for the following tests: ");							//This block of manual tests all returned false as expected.
		System.out.println(urlVal.isValid("http/www.google.com"));
		System.out.println(urlVal.isValid("http://www.google.com:65636"));
		System.out.println(urlVal.isValid("http://www.google.com:65535/test1//file"));
		System.out.println(urlVal.isValid("http://aaa:80"));
		System.out.println(urlVal.isValid("http://go.a:80"));
		System.out.println(urlVal.isValid("http://www.google.com:-1"));
		System.out.println(urlVal.isValid("http://www.google.com:0/#"));
		System.out.println(urlVal.isValid("http://www.google.com:80/#/file"));
		System.out.println(urlVal.isValid("http://255.255.255.256:80"));
		System.out.println(urlVal.isValid("http://1.2.3.4:0"));
		System.out.println(urlVal.isValid("http://go.1aa:65535"));
		System.out.println(urlVal.isValid("http://255.com:65535"));
		System.out.println(urlVal.isValid("http://go.au:80/.."));
		
		System.out.println("Expect True for the following tests: ");							//Returned true so need to figure out why first set of manual test returned false.
		System.out.println(urlVal.isValid("http://go.com"));
		System.out.println(urlVal.isValid("http://go.au"));
		System.out.println(urlVal.isValid("http://0.0.0.0"));
		System.out.println(urlVal.isValid("http://255.255.255.255"));
		
		//Should return true after ":" is removed.
		System.out.println("Expect True for the following tests: ");
		System.out.println(urlVal.isValid("http://www.google.com/test1"));						//Returned true. so ":" is causing false to be returned 
		System.out.println(urlVal.isValid("http://www.google.com/$23"));
		System.out.println(urlVal.isValid("http://www.google.com/$23?action=view"));
		System.out.println(urlVal.isValid("http://www.google.com?action=view"));
		
		//Should return false because of ":".
		System.out.println("Expect false for the following tests: ");
		System.out.println(urlVal.isValid("http://www.google.com:80/test1"));					//Further support that ":" is the cause of false being returned.
	   
		//These 2 tests throw errors and is reported in ProjetPartB.pdf:
		System.out.println(urlVal.isValid("ftp://www.google.com"));
		System.out.println(urlVal.isValid("h3t://go.com"));
   }

   public void testYourFirstPartition(){
	   String append = "www.google.com";
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String[] validScheme = {"http://",
	   			 "ftp://",
	   			 "h3t://",
	   			 ""};

	   String[] invalidScheme = {"3ht:",
	   	   		   "http:/",
	   	   		   "http:",
	   	   		   "http:/",
	   	   		   "://"};

	   System.out.println("-----Valid Scheme Test-----");
	   for (int i = 0; i < validScheme.length; i++) {
		   if (urlVal.isValid(validScheme[i] + append))
		   {
			   System.out.println("PASSED: " + validScheme[i] + append);
		   }
		   else
		   {
			   System.out.println("FAILED: " + validScheme[i] + append);
		   }
	   }
	   
	   System.out.println("-----Invalid Scheme Test-----");
	   for (int i = 0; i < invalidScheme.length; i++) {
		   if (urlVal.isValid(invalidScheme[i] + append))
		   {
			   System.out.println("PASSED: " + invalidScheme[i] + append);
		   }
		   else
		   {
			   System.out.println("FAILED: " + invalidScheme[i] + append);
		   }
	   }
   }
   
   
   public void testYourSecondPartition(){
	   String prepend = "http://";
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String[] validAuthority = {"www.google.com",
	   			 "go.com",
	   			 "go.au",
	   			 "0.0.0.0",
	   			 "255.255.255.255",
	   			 "255.com"};

	   String[] invalidAuthority = {"256.256.256.256",
			   	"255.com",
			   	"1.2.3.4.5",
			   	"1.2.3.4.",
			   	"1.2.3",
			   	".1.2.3.4",
			   	"go.a",
			   	"go.a1a",
			   	"go.1aa",
			   	"aaa.",
			   	".aaa",
			   	"aaa",
			   	""};

	   System.out.println("-----Valid Authority Test-----");
	   for (int i = 0; i < validAuthority.length; i++) {
		   if (urlVal.isValid(prepend + validAuthority[i]))
		   {
			   System.out.println("PASSED: " + prepend + validAuthority[i]);
		   }
		   else
		   {
			   System.out.println("FAILED: " + prepend + validAuthority[i]);
		   }
	   }
	   
	   System.out.println("-----Invalid Authority Test-----");
	   for (int i = 0; i < invalidAuthority.length; i++) {
		   if (urlVal.isValid(prepend + invalidAuthority[i]))
		   {
			   System.out.println("PASSED: " + prepend + invalidAuthority[i]);
		   }
		   else
		   {
			   System.out.println("PASSED: " + prepend + invalidAuthority[i]);
		   }
	   }
   }
   
   public void testYourThirdPartition(){
	   String prepend = "http://www.google.com";
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String[] validPort = {":80",
			   	 ":65535",
	   			 "go.au",
	   			 ":0",
	   			 ""};

	   String[] invalidPort = {":-1",
			   ":65636",
			   	":65a"};

	   System.out.println("-----Valid Port Test-----");
	   for (int i = 0; i < validPort.length; i++) {
		   if (urlVal.isValid(prepend + validPort[i]))
		   {
			   System.out.println("PASSED: " + prepend + validPort[i]);
		   }
		   else
		   {
			   System.out.println("FAILED: " + prepend + validPort[i]);
		   }
	   }
	   
	   System.out.println("-----Invalid Port Test-----");
	   for (int i = 0; i < invalidPort.length; i++) {
		   if (urlVal.isValid(prepend + invalidPort[i]))
		   {
			   System.out.println("PASSED: " + prepend + invalidPort[i]);
		   }
		   else
		   {
			   System.out.println("PASSED: " + prepend + invalidPort[i]);
		   }
	   }
   }
   
   public void testYourFourthPartition(){
	   String prepend = "http://www.google.com:80";
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String[] validPath = {"/test1",
			   "/t123",
			   "/$23",
			   "/test1/",
	   			 "",
	   			"/test1/file"};

	   String[] invalidPath = {":-1",
			   ":65636",
			   	":65a"};

	   System.out.println("-----Valid Path Test-----");
	   for (int i = 0; i < validPath.length; i++) {
		   if (urlVal.isValid(prepend + validPath[i]))
		   {
			   System.out.println("PASSED: " + prepend + validPath[i]);
		   }
		   else
		   {
			   System.out.println("FAILED: " + prepend + validPath[i]);
		   }
	   }
	   
	   System.out.println("-----Invalid Path Test-----");
	   for (int i = 0; i < invalidPath.length; i++) {
		   if (urlVal.isValid(prepend + invalidPath[i]))
		   {
			   System.out.println("PASSED: " + prepend + invalidPath[i]);
		   }
		   else
		   {
			   System.out.println("PASSED: " + prepend + invalidPath[i]);
		   }
	   }
   }
   
   public void testYourFifthPartition(){
	   String prepend = "http://www.google.com:80/test1";
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String[] validPathOptions = {"/test1",
			   "/t123",
			   "/$23",
			   "/test1/",
	   			 "",
	   			"/test1/file",
	   			"/t123/file",
	   			"/$23/file",
	   			"/test1//file"};

	   String[] invalidPathOptions = {"/..",
			   "/../",
			   "/#",
			   "/../file",
			   "/..//file",
			   "/#/file"};

	   System.out.println("-----Valid PathOptions Test-----");
	   for (int i = 0; i < validPathOptions.length; i++) {
		   if (urlVal.isValid(prepend + validPathOptions[i]))
		   {
			   System.out.println("PASSED: " + prepend + validPathOptions[i]);
		   }
		   else
		   {
			   System.out.println("FAILED: " + prepend + validPathOptions[i]);
		   }
	   }
	   
	   System.out.println("-----Invalid PathOptions Test-----");
	   for (int i = 0; i < invalidPathOptions.length; i++) {
		   if (urlVal.isValid(prepend + invalidPathOptions[i]))
		   {
			   System.out.println("PASSED: " + prepend + invalidPathOptions[i]);
		   }
		   else
		   {
			   System.out.println("PASSED: " + prepend + invalidPathOptions[i]);
		   }
	   }
   }
     
   public void testYourSixthPartition(){
	   String prepend = "http://www.google.com:80/test1/test1";
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String[] validQuery = {"?action=view",
			   "?action=edit&mode=up",
	   			""};

	   String[] invalidQuery = {"!action=edit&mode=up",
			   "$action=edit&mode=up"};

	   System.out.println("-----Valid PathOptions Test-----");
	   for (int i = 0; i < validQuery.length; i++) {
		   if (urlVal.isValid(prepend + validQuery[i]))
		   {
			   System.out.println("PASSED: " + prepend + validQuery[i]);
		   }
		   else
		   {
			   System.out.println("FAILED: " + prepend + validQuery[i]);
		   }
	   }
	   
	   System.out.println("-----Invalid PathOptions Test-----");
	   for (int i = 0; i < invalidQuery.length; i++) {
		   if (urlVal.isValid(prepend + invalidQuery[i]))
		   {
			   System.out.println("PASSED: " + prepend + invalidQuery[i]);
		   }
		   else
		   {
			   System.out.println("PASSED: " + prepend + invalidQuery[i]);
		   }
	   }	   
   }

   public void testIsValid()
   {
       String[] schemesUsed = {"http", "https", "ftp", "file", "gopher", "ws", "wss"};
       //RegexValidator tldValidator = new RegexValidator(tldRegex);
       UrlValidator validator = new UrlValidator(schemesUsed, null, 0);

       int numTests = 10000;
       int countIncorrect = 0;
       for (int i = 0; i < numTests; i++){
           //You can use this function for programming based testing
           ResultPair[] curElements = buildRandomURLElements();
           ResultPair curUrl = buildCurrentURL(curElements);

           boolean testResult = validator.isValid(curUrl.item);
           if (testResult != curUrl.valid){
               System.out.println(curUrl.item + " should be " + curUrl.valid);
               countIncorrect++;
           }
       }

        System.out.println("Incorrect tests: " + countIncorrect + "/" + numTests);
        assertEquals(0, countIncorrect);
   }

   public ResultPair[] buildRandomURLElements(){
       Random rand = new Random();

       ResultPair scheme = null;
       ResultPair domain = null;
       ResultPair path = null;
       ResultPair query = null;

       // add a scheme
       int schemeIndex = rand.nextInt(schemes.length);
       scheme = schemes[schemeIndex];

       // randomly choose between address or domain, with 80% balance to domains
       int addressOrDomain = rand.nextInt(10);
       if (addressOrDomain == 1){
           int address = rand.nextInt(ipv4Addresses.length);
           domain = ipv4Addresses[address];
       }
       else if (addressOrDomain == 2){
           int address = rand.nextInt(ipv6Addresses.length);
           domain = ipv6Addresses[address];
       }
       else {
           // have to build a domain
           ResultPair topld = topLevelDomains[rand.nextInt(topLevelDomains.length)];
           ResultPair secondld = secondLevelDomains[rand.nextInt(secondLevelDomains.length)];
           ResultPair thirdld = thirdLevelDomains[rand.nextInt(thirdLevelDomains.length)];

           String combinedDomainLevels = thirdld.item + "." + secondld.item + "." + topld.item;
           Boolean combinedDomainValidity = topld.valid & secondld.valid & thirdld.valid;
           domain = new ResultPair(combinedDomainLevels, !combinedDomainValidity);
       }

       // add a path with 50% probability
       Boolean addPath = rand.nextBoolean();
       if (addPath){
           path = paths[rand.nextInt(paths.length)];
       }
       else {
           path = new ResultPair("", false);
       }

       // add a query with 50% probability
       Boolean addQuery = rand.nextBoolean();
       if (addQuery){
           query = queries[rand.nextInt(queries.length)];
       }
       else {
           query = new ResultPair("", false);
       }

        ResultPair[] currentURLElements = {scheme, domain, path, query};
        return currentURLElements;
   }

   private ResultPair buildCurrentURL(ResultPair[] currentURLElements){
       String URLstring = currentURLElements[0].item + "://" + currentURLElements[1].item;

       // add path if it exists
       if (!currentURLElements[2].item.equals("")){
           URLstring += "/" + currentURLElements[2].item;
       }

       // add query if it exists
       if (!currentURLElements[3].item.equals("")){
           URLstring += "?" + currentURLElements[3].item;
       }

       Boolean URLvalidity = currentURLElements[0].valid;
       for (ResultPair element : currentURLElements){
           URLvalidity &= element.valid;
       }

       return new ResultPair(URLstring, !URLvalidity);
   }

    // populate schemes
    ResultPair[] schemes = {
            new ResultPair("http", false),
            new ResultPair("https", false),
            new ResultPair("ftp", false),
            new ResultPair("file", false),
            new ResultPair("gopher", false),
            new ResultPair("ws", false),
            new ResultPair("wss", false),
            new ResultPair("", true),
            new ResultPair("tls", true)
        };

        // populate ipv4Addresses
    ResultPair[] ipv4Addresses = {
            new ResultPair("192.168.1.1", false),
            new ResultPair("10.0.0.1", false),
            new ResultPair("159.89.42.95", false),
            new ResultPair("35.188.59.75", false),
            new ResultPair("256.1.1.1", true),
            new ResultPair("0.0.0.1000", true)
        };

        // populate ipv6Addresses
    ResultPair[] ipv6Addresses = {
                new ResultPair("2001:db8:a0b:12f0::1", false),
                new ResultPair("::ffff:10.0.0.1/96", false),
                new ResultPair("::ffff:192.168.1.1", false),
                new ResultPair("::ffff:159.89.42.95", false),
                new ResultPair("::ffff:256.1.1.1", true),
                new ResultPair("::ffff:0.0.0.1000", true)
        };

        // populate thirdLevelDomains
    ResultPair[] thirdLevelDomains = {
                new ResultPair("assets1", false),
                new ResultPair("blog", false),
                new ResultPair("careers", false),
                new ResultPair("m", false),
                new ResultPair("cacm", false),
                new ResultPair("/stuff", true)
        };

        // populate secondLevelDomains
    ResultPair[] secondLevelDomains = {
                new ResultPair("bit", false),
                new ResultPair("ipython", false),
                new ResultPair("stackoverflow", false),
                new ResultPair("paxsite", false),
                new ResultPair("steampowered", false),
                new ResultPair("visualgo", false),
                new ResultPair("visual:go", true),
                new ResultPair("[visualgo]", true),
                new ResultPair("visual/go", true)
        };

        // populate topLevelDomains
    ResultPair[] topLevelDomains = {
                new ResultPair("org", false),
                new ResultPair("edu", false),
                new ResultPair("com", false),
                new ResultPair("yoga", false),
                new ResultPair("XN--11B4C3D", false),
                new ResultPair("zu", true),
                new ResultPair("acm", true)
        };

        // populate paths
    ResultPair[] paths = {
                new ResultPair("watch", false),
                new ResultPair("homes/for_sale/globalrelevanceex_sort/37.329239,-121.901583,37.325575,-121.90735_rect/17_zm/", false),
                new ResultPair("1494", false),
                new ResultPair("0KcllPEe8y8", false),
                new ResultPair("start/videomeeting", false),
                new ResultPair("SB10001424052970203833004577249512827646658", false),
                new ResultPair("signup/skipped?", true)
        };

        // populate queries
    ResultPair[] queries = {
                new ResultPair("active%5fpage=120&active%5fpage%5fstr=page%5fsystem%5fsettings&req%5fmode=0&mimic%5fbutton%5ffield=goto%3a+120%2e%2e&strip%5fpage%5ftop=0&button%5fvalue=120", false),
                new ResultPair("title=Special:Search&search=macos", false),
                new ResultPair("t=104474", false),
                new ResultPair("", false),
                new ResultPair("LinkId=528882", false),
                new ResultPair("type=click&enid=ZWFzPTEmbXNpZD0mYXVpZD0mbWFpbGluZ2lkPTIwMTgwMzE0Ljg2OTUxMTkxJm1lc3NhZ2VpZD1NREItUFJELUJVTC0yMDE4MDMxNC44Njk1MTE5MSZkYXRhYmFzZWlkPTEwMDEmc2VyaWFsPTE4MjU5NjIyJmVtYWlsaWQ9d2lsbHdhZ25lcjYwMkBnbWFpbC5jb20mdXNlcmlkPXdpbGx3YWduZXI2MDJAZ21haWwuY29tJnRhcmdldGlkPSZmbD0mZXh0cmE9TXVsdGl2YXJpYXRlSWQ9JiYm&&&104&&&https://www.healthcare.gov/login?utm_campaign=20180314chdlfs1ccupdrsnm1&utm_content=english&utm_medium=email&utm_source=govdelivery", false)
        };

    String tldRegex = "/^((?:http|https)):\\/\\/(?=[a-z\\d])((?:(?:(?!_|\\.\\.|-\\.|\\.-|\\.\\/|-\\/)[\\w-\\.])+?)(?:[\\.](?:aaa|aarp|abb|abbott|abbvie|abogado|abudhabi|ac|academy|accenture|accountant|accountants|aco|active|actor|ad|adac|ads|adult|ae|aeg|aero|aetna|af|afl|ag|agakhan|agency|ai|aig|airbus|airforce|airtel|akdn|al|alibaba|alipay|allfinanz|ally|alsace|alstom|am|amica|amsterdam|analytics|android|anquan|ao|apartments|app|apple|aq|aquarelle|ar|aramco|archi|army|arpa|arte|as|asia|associates|at|attorney|au|auction|audi|audible|audio|author|auto|autos|avianca|aw|aws|ax|axa|az|azure|ba|baby|baidu|band|bank|bar|barcelona|barclaycard|barclays|barefoot|bargains|bauhaus|bayern|bb|bbc|bbva|bcg|bcn|bd|be|beats|beer|bentley|berlin|best|bet|bf|bg|bh|bharti|bi|bible|bid|bike|bing|bingo|bio|biz|bj|black|blackfriday|blog|bloomberg|blue|bm|bms|bmw|bn|bnl|bnpparibas|bo|boats|boehringer|bom|bond|boo|book|boots|bosch|bostik|bot|boutique|br|bradesco|bridgestone|broadway|broker|brother|brussels|bs|bt|budapest|bugatti|build|builders|business|buy|buzz|bv|bw|by|bz|bzh|ca|cab|cafe|cal|call|cam|camera|camp|cancerresearch|canon|capetown|capital|car|caravan|cards|care|career|careers|cars|cartier|casa|cash|casino|cat|catering|cba|cbn|cc|cd|ceb|center|ceo|cern|cf|cfa|cfd|cg|ch|chanel|channel|chase|chat|cheap|chintai|chloe|christmas|chrome|church|ci|cipriani|circle|cisco|citic|city|cityeats|ck|cl|claims|cleaning|click|clinic|clinique|clothing|cloud|club|clubmed|cm|cn|co|coach|codes|coffee|college|cologne|com|commbank|community|company|compare|computer|comsec|condos|construction|consulting|contact|contractors|cooking|cool|coop|corsica|country|coupon|coupons|courses|cr|credit|creditcard|creditunion|cricket|crown|crs|cruises|csc|cu|cuisinella|cv|cw|cx|cy|cymru|cyou|cz|dabur|dad|dance|date|dating|datsun|day|dclk|dds|de|deal|dealer|deals|degree|delivery|dell|deloitte|delta|democrat|dental|dentist|desi|design|dev|dhl|diamonds|diet|digital|direct|directory|discount|dj|dk|dm|dnp|do|docs|dog|doha|domains|dot|download|drive|dtv|dubai|dunlop|dupont|durban|dvag|dz|earth|eat|ec|edeka|edu|education|ee|eg|email|emerck|energy|engineer|engineering|enterprises|epost|epson|equipment|er|ericsson|erni|es|esq|estate|et|eu|eurovision|eus|events|everbank|exchange|expert|exposed|express|extraspace|fage|fail|fairwinds|faith|family|fan|fans|farm|fashion|fast|feedback|ferrero|fi|film|final|finance|financial|fire|firestone|firmdale|fish|fishing|fit|fitness|fj|fk|flickr|flights|flir|florist|flowers|flsmidth|fly|fm|fo|foo|football|ford|forex|forsale|forum|foundation|fox|fr|fresenius|frl|frogans|frontier|ftr|fund|furniture|futbol|fyi|ga|gal|gallery|gallo|gallup|game|games|garden|gb|gbiz|gd|gdn|ge|gea|gent|genting|gf|gg|ggee|gh|gi|gift|gifts|gives|giving|gl|glass|gle|global|globo|gm|gmail|gmbh|gmo|gmx|gn|gold|goldpoint|golf|goo|goodyear|goog|google|gop|got|gov|gp|gq|gr|grainger|graphics|gratis|green|gripe|group|gs|gt|gu|guardian|gucci|guge|guide|guitars|guru|gw|gy|hamburg|hangout|haus|hdfcbank|health|healthcare|help|helsinki|here|hermes|hiphop|hisamitsu|hitachi|hiv|hk|hkt|hm|hn|hockey|holdings|holiday|homedepot|homes|honda|horse|host|hosting|hoteles|hotmail|house|how|hr|hsbc|ht|htc|hu|hyundai|ibm|icbc|ice|icu|id|ie|ifm|iinet|il|im|imamat|imdb|immo|immobilien|in|industries|infiniti|info|ing|ink|institute|insurance|insure|int|international|investments|io|ipiranga|iq|ir|irish|is|iselect|ismaili|ist|istanbul|it|itau|iwc|jaguar|java|jcb|jcp|je|jetzt|jewelry|jlc|jll|jm|jmp|jnj|jo|jobs|joburg|jot|joy|jp|jpmorgan|jprs|juegos|kaufen|kddi|ke|kerryhotels|kerrylogistics|kerryproperties|kfh|kg|kh|ki|kia|kim|kinder|kindle|kitchen|kiwi|km|kn|koeln|komatsu|kosher|kp|kpmg|kpn|kr|krd|kred|kuokgroup|kw|ky|kyoto|kz|la|lacaixa|lamborghini|lamer|lancaster|land|landrover|lanxess|lasalle|lat|latrobe|law|lawyer|lb|lc|lds|lease|leclerc|legal|lego|lexus|lgbt|li|liaison|lidl|life|lifeinsurance|lifestyle|lighting|like|limited|limo|lincoln|linde|link|lipsy|live|living|lixil|lk|loan|loans|locker|locus|lol|london|lotte|lotto|love|lr|ls|lt|ltd|ltda|lu|lupin|luxe|luxury|lv|ly|ma|madrid|maif|maison|makeup|man|management|mango|market|marketing|markets|marriott|mattel|mba|mc|md|me|med|media|meet|melbourne|meme|memorial|men|menu|meo|metlife|mg|mh|miami|microsoft|mil|mini|mk|ml|mlb|mls|mm|mma|mn|mo|mobi|mobily|moda|moe|moi|mom|monash|money|montblanc|mormon|mortgage|moscow|motorcycles|mov|movie|movistar|mp|mq|mr|ms|mt|mtn|mtpc|mtr|mu|museum|mutual|mutuelle|mv|mw|mx|my|mz|na|nadex|nagoya|name|natura|navy|nc|ne|nec|net|netbank|netflix|network|neustar|new|news|next|nextdirect|nexus|nf|ng|ngo|nhk|ni|nico|nikon|ninja|nissan|nissay|nl|no|nokia|northwesternmutual|norton|now|nowruz|nowtv|np|nr|nra|nrw|ntt|nu|nyc|nz|obi|office|okinawa|olayan|olayangroup|ollo|om|omega|one|ong|onl|online|ooo|oracle|orange|org|organic|origins|osaka|otsuka|ott|ovh|pa|page|pamperedchef|panerai|paris|pars|partners|parts|party|passagens|pccw|pe|pet|pf|pg|ph|pharmacy|philips|photo|photography|photos|physio|piaget|pics|pictet|pictures|pid|pin|ping|pink|pioneer|pizza|pk|pl|place|play|playstation|plumbing|plus|pm|pn|pohl|poker|porn|post|pr|praxi|press|prime|pro|prod|productions|prof|progressive|promo|properties|property|protection|ps|pt|pub|pw|pwc|py|qa|qpon|quebec|quest|racing|re|read|realestate|realtor|realty|recipes|red|redstone|redumbrella|rehab|reise|reisen|reit|ren|rent|rentals|repair|report|republican|rest|restaurant|review|reviews|rexroth|rich|richardli|ricoh|rio|rip|ro|rocher|rocks|rodeo|room|rs|rsvp|ru|ruhr|run|rw|rwe|ryukyu|sa|saarland|safe|safety|sakura|sale|salon|samsung|sandvik|sandvikcoromant|sanofi|sap|sapo|sarl|sas|save|saxo|sb|sbi|sbs|sc|sca|scb|schaeffler|schmidt|scholarships|school|schule|schwarz|science|scor|scot|sd|se|seat|security|seek|select|sener|services|seven|sew|sex|sexy|sfr|sg|sh|sharp|shaw|shell|shia|shiksha|shoes|shop|shouji|show|shriram|si|silk|sina|singles|site|sj|sk|ski|skin|sky|skype|sl|sm|smile|sn|sncf|so|soccer|social|softbank|software|sohu|solar|solutions|song|sony|soy|space|spiegel|spot|spreadbetting|sr|srl|st|stada|star|starhub|statebank|statefarm|statoil|stc|stcgroup|stockholm|storage|store|stream|studio|study|style|su|sucks|supplies|supply|support|surf|surgery|suzuki|sv|swatch|swiss|sx|sy|sydney|symantec|systems|sz|tab|taipei|talk|taobao|tatamotors|tatar|tattoo|tax|taxi|tc|tci|td|tdk|team|tech|technology|tel|telecity|telefonica|temasek|tennis|teva|tf|tg|th|thd|theater|theatre|tickets|tienda|tiffany|tips|tires|tirol|tj|tk|tl|tm|tmall|tn|to|today|tokyo|tools|top|toray|toshiba|total|tours|town|toyota|toys|tr|trade|trading|training|travel|travelers|travelersinsurance|trust|trv|tt|tube|tui|tunes|tushu|tv|tvs|tw|tz|ua|ubs|ug|uk|unicom|university|uno|uol|ups|us|uy|uz|va|vacations|vana|vc|ve|vegas|ventures|verisign|versicherung|vet|vg|vi|viajes|video|vig|viking|villas|vin|vip|virgin|vision|vista|vistaprint|viva|vlaanderen|vn|vodka|volkswagen|vote|voting|voto|voyage|vu|vuelos|wales|walter|wang|wanggou|warman|watch|watches|weather|weatherchannel|webcam|weber|website|wed|wedding|weibo|weir|wf|whoswho|wien|wiki|williamhill|win|windows|wine|wme|wolterskluwer|work|works|world|ws|wtc|wtf|xbox|xerox|xihuan|xin|xn--11b4c3d|xn--1ck2e1b|xn--1qqw23a|xn--30rr7y|xn--3bst00m|xn--3ds443g|xn--3e0b707e|xn--3pxu8k|xn--42c2d9a|xn--45brj9c|xn--45q11c|xn--4gbrim|xn--55qw42g|xn--55qx5d|xn--5tzm5g|xn--6frz82g|xn--6qq986b3xl|xn--80adxhks|xn--80ao21a|xn--80asehdb|xn--80aswg|xn--8y0a063a|xn--90a3ac|xn--90ais|xn--9dbq2a|xn--9et52u|xn--9krt00a|xn--b4w605ferd|xn--bck1b9a5dre4c|xn--c1avg|xn--c2br7g|xn--cck2b3b|xn--cg4bki|xn--clchc0ea0b2g2a9gcd|xn--czr694b|xn--czrs0t|xn--czru2d|xn--d1acj3b|xn--d1alf|xn--e1a4c|xn--eckvdtc9d|xn--efvy88h|xn--estv75g|xn--fct429k|xn--fhbei|xn--fiq228c5hs|xn--fiq64b|xn--fiqs8s|xn--fiqz9s|xn--fjq720a|xn--flw351e|xn--fpcrj9c3d|xn--fzc2c9e2c|xn--fzys8d69uvgm|xn--g2xx48c|xn--gckr3f0f|xn--gecrj9c|xn--h2brj9c|xn--hxt814e|xn--i1b6b1a6a2e|xn--imr513n|xn--io0a7i|xn--j1aef|xn--j1amh|xn--j6w193g|xn--jlq61u9w7b|xn--jvr189m|xn--kcrx77d1x4a|xn--kprw13d|xn--kpry57d|xn--kpu716f|xn--kput3i|xn--l1acc|xn--lgbbat1ad8j|xn--mgb9awbf|xn--mgba3a3ejt|xn--mgba3a4f16a|xn--mgba7c0bbn0a|xn--mgbaam7a8h|xn--mgbab2bd|xn--mgbayh7gpa|xn--mgbb9fbpob|xn--mgbbh1a71e|xn--mgbc0a9azcg|xn--mgbca7dzdo|xn--mgberp4a5d4ar|xn--mgbpl2fh|xn--mgbt3dhd|xn--mgbtx2b|xn--mgbx4cd0ab|xn--mix891f|xn--mk1bu44c|xn--mxtq1m|xn--ngbc5azd|xn--ngbe9e0a|xn--node|xn--nqv7f|xn--nqv7fs00ema|xn--nyqy26a|xn--o3cw4h|xn--ogbpf8fl|xn--p1acf|xn--p1ai|xn--pbt977c|xn--pgbs0dh|xn--pssy2u|xn--q9jyb4c|xn--qcka1pmc|xn--qxam|xn--rhqv96g|xn--rovu88b|xn--s9brj9c|xn--ses554g|xn--t60b56a|xn--tckwe|xn--unup4y|xn--vermgensberater-ctb|xn--vermgensberatung-pwb|xn--vhquv|xn--vuq861b|xn--w4r85el8fhu5dnra|xn--w4rs40l|xn--wgbh1c|xn--wgbl6a|xn--xhq521b|xn--xkc2al3hye2a|xn--xkc2dl3a5ee0h|xn--y9a3aq|xn--yfro4i67o|xn--ygbi2ammx|xn--zfr164b|xperia|xxx|xyz|yachts|yahoo|yamaxun|yandex|ye|yodobashi|yoga|yokohama|you|youtube|yt|yun|za|zappos|zara|zero|zip|zm|zone|zuerich|zw)))\\/([\\w-\\.~:\\/?#\\[\\]@!$&\\'\\(\\)*+,;=]*)$/i\n";
}
