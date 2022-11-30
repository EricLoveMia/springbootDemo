package com.loveprogrammer.springboot.elasticsearch.dao;

import com.loveprogrammer.springboot.elasticsearch.domain.PhoneModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: BlogRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/6/27 9:47
 **/
public interface PhoneModelRepository extends ElasticsearchRepository<PhoneModel, String> {


}
