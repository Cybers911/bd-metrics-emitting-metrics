import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

/**
 * Contains operations for publishing metrics.
 */
public class MetricsPublisher {

    private AmazonCloudWatch cloudWatch = AmazonCloudWatchClientBuilder.standard().withRegion(Regions.US_WEST_2).build();


    /**
     * Publishes the given metric to CloudWatch.
     *
     * @param metricName name of metric to publish.
     * @param value value of metric.
     * @param unit unit of metric.
     */
    public void addMetric(final String metricName, final double value, final StandardUnit unit) {
        final PutMetricDataRequest request = buildMetricDataRequest(metricName, value, unit);
        cloudWatch.putMetricData(request);
    }

    /**
     * Helper method that builds the PutMetricDataRequest object used to publish to CloudWatch.
     *
     * @param metricName name of metric
     * @param value value of metric
     * @param unit unit of metric.
     * @return PutMetricDataRequest
     */
    public PutMetricDataRequest buildMetricDataRequest(final String metricName, final double value,
                                                        final StandardUnit unit) {
        Dimension dimension = new Dimension()
                // Replace "ENVIRONMENT" with the actual dimension name
               .withName("ENVIRONMENT")
                // Replace "PRODUCTION" with the actual dimension value
               .withValue("PRODUCTION");

        MetricDatum datum = new MetricDatum()
                // Replace "MyCustomMetric" with the actual metric name
               .withMetricName(metricName)
                // Replace 123.45 with the actual value
               .withValue(value)
                // Replace "Seconds" with the actual unit
               .withUnit(unit);
               datum.withDimensions(dimension);

                PutMetricDataRequest request = new PutMetricDataRequest()
                        // Replace "EXAMPLE/ORDERS" with the actual namespace
               .withNamespace("EXAMPLE/ORDERS")
                        // Add the created MetricDatum to the request
               .withMetricData(datum);

                return request;


        // TODO: implement





    }
}
