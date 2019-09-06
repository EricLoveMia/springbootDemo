package cn.eric.springbootdemo.dao;

import cn.eric.springbootdemo.domain.Players;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: PlayersRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/6/27 9:47
 **/
public interface PlayersRepository extends ElasticsearchRepository<Players, String> {


}
