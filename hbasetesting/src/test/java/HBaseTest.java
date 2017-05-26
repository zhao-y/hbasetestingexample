import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HBaseTest {

    private HBaseTestingUtility hBaseTestingUtility = new HBaseTestingUtility();
    private byte[] table = Bytes.toBytes("testTable");
    private byte[] family = Bytes.toBytes("f1");
    private HTable hTable;

    @Before
    public void before() throws Exception {
//        start mini cluster before first test started
        hBaseTestingUtility.startMiniCluster();
//        create table
        hBaseTestingUtility.createTable(table, family);
//        get configuration from hBaseTestingUtility and create HTable
        hTable = new HTable(hBaseTestingUtility.getConfiguration(), table);
    }

    @After
    public void after() throws Exception {
//        shutdown mini cluster after all test are done
        hBaseTestingUtility.shutdownMiniCluster();
    }

    @Test
    public void testPutAngGet() throws Exception {
        byte[] targetRow = Bytes.toBytes("row1");
        byte[] targetColumn = Bytes.toBytes("c1");
        byte[] value = Bytes.toBytes("v1");

        Put put = new Put(targetRow);
        put.add(family, targetColumn, value);
        hTable.put(put);

        Get get = new Get(targetRow);

        assertThat(hTable.get(get).getValue(family, targetColumn), is(value));
    }
}
