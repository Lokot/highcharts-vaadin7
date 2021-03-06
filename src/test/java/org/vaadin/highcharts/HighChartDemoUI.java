package org.vaadin.highcharts;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.vaadin.aceeditor.AceEditor;
import org.vaadin.aceeditor.AceMode;

import javax.servlet.annotation.WebServlet;
import java.util.Scanner;

/**
 * This is the demo application demonstrating the use of this Highcharts Vaadin wrapper.
 * It uses the nice AceEditor add-on that provides an editor with JavaScript syntax highlighting for editing the
 * chart code.
 *
 * @author Stefan Endrullis
 */
@SuppressWarnings("serial")
public class HighChartDemoUI extends UI {

	public static final String INITIAL_HCJS = new Scanner(HighChartDemoUI.class.getResourceAsStream("highchartsExampleCode.js"), "UTF-8").useDelimiter("\\A").next();

	@WebServlet(value = "/highchart/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = HighChartDemoUI.class, widgetset = "org.vaadin.highcharts.DemoWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final AceEditor chartCodeField = new AceEditor() {{
			setCaption("Chart Code");
			setSizeFull();
			setMode(AceMode.javascript);
			setValue(INITIAL_HCJS);
		}};

		final HighChart chart = new HighChart() {{
			setSizeFull();
			setHcjs(chartCodeField.getValue());

			addFunction("onClick", (JavaScriptFunction) args -> {
				Notification.show("Chart clicked: (" + args.getNumber(0) + ", " + args.getNumber(1) + ")", Notification.Type.TRAY_NOTIFICATION);

				manipulateChart(
						"chart.addSeries({\n" +
						"    name: 'pos',\n" +
						"    data: [{x: " + args.getNumber(0) + ", y: " + args.getNumber(1) + "}]\n" +
						"});"
				);
			});
		}};

		chartCodeField.addValueChangeListener(e -> chart.setHcjs(e.getValue()));

		HorizontalLayout horizontalLayout = new HorizontalLayout() {{
			setSizeFull();
			setMargin(true);
			setSpacing(true);
			addComponent(chartCodeField);
			setExpandRatio(chartCodeField, 1);
			addComponent(chart);
			setExpandRatio(chart, 1);
		}};

		setContent(horizontalLayout);
	}

}
