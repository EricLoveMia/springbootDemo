package cn.eric.thrift.controller;

import cn.eric.thrift.service.RPCThriftClient;
import cn.eric.thrift.service.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RPCThriftController
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/2
 * @Version V1.0
 **/
@RestController
@Api(value = "SpringBoot集成Thrift实现RPC远程调用", tags = "RPCThriftController")
public class RPCThriftController {
    private final Logger logger = LoggerFactory.getLogger(RPCThriftController.class);
    @Autowired
    RPCThriftClient rpcThriftClient;

    @GetMapping(value = "/thrift/{name}")
    @ApiOperation(value = "第一个接口Hello，World!", notes = "测试接口", response = String.class)
    public String thriftTest(@PathVariable String name) {
        try {
            rpcThriftClient.open();
            return rpcThriftClient.getRPCThriftService().getDate(name);
        } catch (Exception e) {
            logger.error("RPC调用失败", e);
            return "error";
        } finally {
            rpcThriftClient.close();
        }
    }

    @PostMapping(value = "/setStudent")
    @ApiOperation(value = "添加一个学生对象", notes = "测试结构体添加...", response = ApiResponse.class)
    @ApiImplicitParam(name = "student", value = "学生实体", required = true, dataType = "Student")
    public Result setStudent(@RequestBody Student student) {
        try {
            rpcThriftClient.open();
            return Result.ok(rpcThriftClient.getRPCThriftService().postStudent(student));
        } catch (Exception e) {
            logger.error("RPC调用失败", e);
            return Result.error(500, "添加失敗");
        } finally {
            rpcThriftClient.close();
        }
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    @ApiOperation(value = "添加一个学生对象", notes = "测试接口", response = String.class)
    @ApiImplicitParam(name = "userId", value = "获取学生信息", required = true, dataType = "String")
    public Result getStudent(String userId) {
        try {
            rpcThriftClient.open();
            return Result.ok(rpcThriftClient.getRPCThriftService().getStudent(Integer.parseInt(userId)));
        } catch (Exception e) {
            logger.error("RPC调用失败", e);
            return Result.error(500, "获取失敗");
        } finally {
            rpcThriftClient.close();
        }
    }
}