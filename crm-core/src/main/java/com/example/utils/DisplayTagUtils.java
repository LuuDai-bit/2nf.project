package com.example.utils;

import com.example.dto.BaseDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;

public class DisplayTagUtils {
    private static final Logger log = Logger.getLogger(DisplayTagUtils.class);

    public static void initSearchBean(HttpServletRequest request, BaseDTO bean){
        if (bean != null){
            String sortExpression = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORTUSINGNAME));
            String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORTUSINGNAME));
            String sPage = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORTUSINGNAME));
            Integer page = 1;
            if(StringUtils.isNotBlank(sPage)){
                try{
                    page=Integer.valueOf(sPage);
                }catch (Exception e){
                    log.error(e.getMessage());
                }
            }
            bean.setPage(page);
            bean.setSortExpression(sortExpression);
            bean.setSortDirection(sortDirection);
        }


    }
}
