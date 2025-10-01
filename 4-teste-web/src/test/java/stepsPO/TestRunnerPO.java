package stepsPO;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("featuresPO")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "stepsPO")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports-po")
public class TestRunnerPO {
}