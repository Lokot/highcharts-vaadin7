# Highcharts Add-on for Vaadin 7+

This project wraps the JavaScript library [highcharts](http://www.highcharts.com/) for use with Vaadin 7 and 8.

## Online demo

Try one of the add-on demos:
* [Demo with HighChart](https://endrullis.de/highcharts-vaadin7/).
* [Demo with StockChart](https://endrullis.de/highcharts-vaadin7/stockchart).

## Download release

Official releases of this add-on are available at Vaadin Directory.
For Maven instructions, download and reviews, go to [https://vaadin.com/addon/highcharts-for-vaadin-7](https://vaadin.com/addon/highcharts-for-vaadin-7).

## How to use it

1. Download jquery and `highcharts.js` (if not already loaded in your webapp) and save them in the resource directory `org/vaadin/highcharts`.

2. Create a new class in the package `org.vaadin.highcharts` (e.g. `HighChart`) and inherit it from `AbstractHighChart`.

3. Add a proper `@JavaScript` annotation to the newly created class in order to load all necessary JavaScript libraries that
   you need (e.g. `jquery.js`, `highcharts.js`, `highcharts-more.js`, ...). Make sure your project complies with the licenses of those libraries.
   At the end of this list add "highcharts-connector.js".  Here is an example:

	```java
	package org.vaadin.highcharts;
	
	@JavaScript({"jquery-min.js", "highcharts.js", "highcharts-connector.js"})
	public class HighChart extends AbstractHighChart {
	    private static final long serialVersionUID = -7326315426217377753L;
	}
	```

4. Instantiate a HighChart object, set the chart code, and add it to the layout of your UI:

	```java
	// Create and configure a chart.
	HighChart chart = new HighChart();
	chart.setHcjs("var options = { title: { text: 'test diagram' }, series: [{ name: 's1', data: [1, 3, 2]}] };")
	someLayout.add(chart)
	```

## Download the source code

	git clone https://github.com/xylo/highcharts-vaadin7.git
	cd highcharts-vaadin7

## Building and running the demo in a standalone jetty server

	mvn package
	mvn -Pdemo exec:java

To see the demo, navigate to http://localhost:9998/

## Development instructions 

1. Import to your favourite IDE
2. Run the `main` method of the `Server` class (`org.vaadin.highcharts.uiserver.Server`) to launch embedded web server that lists all your test UIs at `http://localhost:9998`
3. Code and test
   * create UI's for various use cases for your add-ons, see examples. These can also work as usage examples for your add-on users.
   * create browser level and integration tests under src/test/java/
   * Browser level tests are executed manually from IDE (JUnit case) or with Maven profile `browsertests` (`mvn verify -Pbrowsertests`). If you have a setup for solidly working Selenium driver(s), consider enabling that profile by default.
4. Test also in real world projects, e.g. create a demo project, build a snapshot release (`mvn install`) and use the snapshot build in it.

## Creating releases

1. Use Maven release plugin (mvn release:prepare; mvn release:perform)
2. Upload the ZIP file generated to target/checkout/target directory to https://vaadin.com/directory service (and/or optionally publish your add-on to Maven central)

 
## Release notes

### Version 2.0
- made add-on compatible with vaadin 8

### Version 1.3
- added support for stock charts

### Version 1.2
- added function `manipulateChart` for manipulating charts dynamically
- restructured the whole project (from multi-module to single-module)

### Version 1.1
- minor changes

### Version 1.0
- initial version of the Highcharts wrapper for Vaadin 7

## Roadmap

This component is developed as a hobby with no public roadmap or any guarantees of upcoming releases.

## Issue tracking

The issues for this add-on are tracked on its github.com page. All bug reports and feature requests are appreciated. 

## Contributions

Contributions are welcome, but there are no guarantees that they are accepted as such. Process for contributing is the following:
- Fork this project
- Create an issue to this project about the contribution (bug or feature) if there is no such issue about it already. Try to keep the scope minimal.
- Develop and test the fix or functionality carefully. Only include minimum amount of code needed to fix the issue.
- Refer to the fixed issue in commit
- Send a pull request for the original project
- Comment on the original issue that you have implemented a fix for it

## License & Author

Add-on is distributed under Apache License 2.0. For license terms, see LICENSE.txt.

Highcharts for Vaadin 7+ is written by Stefan Endrullis.

<!--
# Developer Guide

## Getting started

Here is a simple example on how to try out the add-on component:

<...>

For a more comprehensive example, see highcharts-demo/src/main/java/org/vaadin/highcharts/demo/DemoUI.java

## Features

### Feature A

<...>

### Feature B

<...>

### Feature C

<...>

## API

MyComponent JavaDoc is available online at <...>
-->
