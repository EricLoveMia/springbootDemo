package cn.eric.springbootsharding.repositorty;

import cn.eric.springbootsharding.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: OrderRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/5/22 17:46
 **/
public interface OrderRepository extends CrudRepository<Order, Long> {


}


