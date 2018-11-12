package com.arbiva.apsfl.tms.daoImpl;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author srinivasa
 *
 */
@Repository("storedProcedureDAO")
public class StoredProcedureDAO {
    
    private static final Logger LOGGER = Logger.getLogger(StoredProcedureDAO.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 
     * @return
     */
    public Long executeStoredProcedure(String type) {
        Long sequenceNo = 0L;
        SimpleJdbcCall generateSequenceSP = null;
        Map<String,Object> in = null;
        Map<String, Object> out = null;
        try{
            generateSequenceSP = new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_nextid").withoutProcedureColumnMetaDataAccess().useInParameterNames("p_idname").declareParameters(new SqlParameter("p_idname",Types.VARCHAR), new SqlOutParameter("p_nextid", Types.BIGINT), new SqlOutParameter("p_result", Types.VARCHAR));
            in = new HashMap<String, Object>();
            in.put("p_idname", type);
            out = generateSequenceSP.execute(in);            
            if(out.get("p_nextid") != null)
                sequenceNo = Long.valueOf(out.get("p_nextid").toString());
        }
        catch(Exception ex) {
            LOGGER.error("Exception occurred during getCafNo(): " + ex); 
        }
        finally {
            generateSequenceSP = null;
            in = null;
            out = null;
        }
        return sequenceNo;
    }
}