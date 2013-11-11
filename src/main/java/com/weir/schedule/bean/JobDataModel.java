/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.bean;

import com.weir.schedule.model.JobData;
import com.weir.schedule.service.SchedulerManager;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.primefaces.model.SelectableDataModel;
import org.quartz.SchedulerException;

/**
 *
 * @author Weir
 */
public class JobDataModel extends ListDataModel<JobData> implements SelectableDataModel<JobData> {

    private static Logger log = Logger.getLogger(SchedulerManager.class);
    @Inject
    private SchedulerManager manager;

    public JobDataModel() {
    }

    public JobDataModel(List<JobData> list) {
        super(list);
    }

    @Override
    public Object getRowKey(JobData job) {
        return job.getName();
    }

    @Override
    public JobData getRowData(String rowKey) {
        try {
            List<JobData> lst = manager.getAllJob();
            for (Iterator<JobData> it = lst.iterator(); it.hasNext();) {
                JobData job = it.next();
                if (job.getName().equals(rowKey)) {
                    return job;
                }
            }
            return null;
        } catch (SchedulerException ex) {
            log.error(ex);
            return null;
        }
    }
}
