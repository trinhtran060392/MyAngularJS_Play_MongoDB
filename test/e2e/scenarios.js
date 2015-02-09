'use strict';

http://docs.angularjs.org/guide/dev_guide.e2e-testing 

describe('Student App', function() {
 
  it('should redirect index.html to index.html#/', function() {
    browser.get('/');
    browser.getLocationAbsUrl().then(function(url) {
        expect(url.split('#')[1]).toBe('/');
    });
  });
  describe('Student list view', function() {
    
    beforeEach(function() {
      browser.get('/');
    });
    
    it('should show 5 student on view', function() {
      var studentList = element.all(by.repeater('student in students'));
      
      expect(studentList.count()).toBe(4);
      
      var query = element(by.model('query'));
      query.sendKeys('trinh');
      expect(studentList.count()).toBe(2);
      
    });
    
    it('should be possible to control students order via the drop down select box', function() {
      
      var phoneName = element.all(by.repeater('student in students').column('student.name'));
      
      var query = element(by.model('query'));
      function getNames() {
        return phoneName.map(function(elm) {
          return elm.getText();
        });
      }
      
      query.sendKeys('trinh');
      
      expect(getNames()).toEqual([
       "trinh",
       "trinh tran"
      ]);
      
      element(by.model('orderProp')).element(by.css('option[value="age"]')).click();
      
      expect(getNames()).toEqual([
      "trinh tran",
      "trinh"
      ]);
    });
    
    it('should be possible to control get boy and girl list view ', function() {
      
      var studentList = element.all(by.repeater('student in students'));
      element(by.css('.icon-group')).click();
      element(by.css('.icon-user')).click();
      
      expect(studentList.count()).toBe(1);
      
      element(by.css('.icon-girl')).click();
      
      expect(studentList.count()).toBe(3);
      
    });
    
  });
  describe('Student detail view', function() {
    
    beforeEach(function() {
      browser.get('#/studentsdetail/99910eda-0ff3-458a-9116-44c2fbee8787');
    });
    
    it('should display trinh page', function() {
      expect(element(by.binding('studentDetail.name')).getText()).toBe('trinh');
    });
  });
  
});

/*'use strict';

 http://docs.angularjs.org/guide/dev_guide.e2e-testing 

describe('PhoneCat App', function() {
	describe('Phone list view', function(){

	  beforeEach(function() {
	    browser.get('app/index.html#phones');
	  });
	  it('should filter the phone list as user types into the search box', function(){
	  	var phoneList = element.all(by.repeater('phone in phones'));
	  	var query = element(by.model('query'));
	  	expect(phoneList.count()).toBe(20);
	  	query.sendKeys('nexus');
      expect(phoneList.count()).toBe(1);

	  	query.clear();
	  	query.sendKeys('motorola');
	  	expect(phoneList.count()).toBe(8);
	  });

	  it('should be possible to control phone order via the dropdown select box',function(){

	  	var phoneNameColumn = element.all(by.repeater('phone in phones').column('phone.name'));
	  	var query = element(by.model('query'));

	  	function getNames(){
	  		return phoneNameColumn.map(function(elm){
	  			return elm.getText();
	  		});
	  	}
	  	query.sendKeys('tablet');
	  	expect(getNames()).toEqual(["Motorola XOOM\u2122 with Wi-Fi",
	  			"MOTOROLA XOOM\u2122"
	  	]);
	  	element(by.model('orderProp')).element(by.css('option[value="name"]')).click();

	  	expect(getNames()).toEqual(["MOTOROLA XOOM\u2122",
				 "Motorola XOOM\u2122 with Wi-Fi"]
			);
	  });
	  it('should render phone specific links', function() {
	  	var query = element(by.model('query'));
	  	query.sendKeys('nexus');
	  	element(by.css('.phones li a')).click();
	  	browser.getLocationAbsUrl().then(function(url){
	  		expect(url.split('#')[1]).toBe('/phones/nexus-s');;

	  	});
	  });

	});
 	describe('Phone Detail view', function(){

	  	beforeEach(function(){
	  		browser.get('app/index.html#/phones/nexus-s');
	  	});
	  	it('should display nexus-s page', function(){
	  		expect(element(by.binding('phone.name')).getText()).toBe('Nexus S');
	  	});
	  	it('should display the first phone image as the main phone image', function(){
	  		expect(element(by.css('img.phone.active')).getAttribute('src')).toMatch(/img\/phones\/nexus-s.0.jpg/);
	  	});
	  	it('should swap main image if a thumbnail image is clicked on', function(){

	  		element(by.css('.phone-thumbs li:nth-child(3) img')).click();
	  		expect(element(by.css('img.phone.active')).getAttribute('src')).toMatch(/img\/phones\/nexus-s.2.jpg/);

	  		element(by.css('.phone-thumbs li:nth-child(1) img')).click();
	  		expect(element(by.css('img.phone.active')).getAttribute('src')).toMatch(/img\/phones\/nexus-s.0.jpg/);
	  	});
	});

});*/