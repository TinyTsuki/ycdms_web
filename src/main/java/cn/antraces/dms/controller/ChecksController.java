package cn.antraces.dms.controller;

import cn.antraces.dms.entity.Checks;
import cn.antraces.dms.service.ChecksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Checks)表控制层
 *
 * @author silver
 * @since 2021-11-16 16:24:33
 */
@RestController
@RequestMapping("api/Check")
public class ChecksController {
    /**
     * 服务对象
     */
    @Resource
    private ChecksService checksService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Checks> queryById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.checksService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param checks 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Checks> add(Checks checks) {
        return ResponseEntity.ok(this.checksService.insert(checks));
    }

    /**
     * 编辑数据
     *
     * @param checks 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Checks> edit(Checks checks) {
        return ResponseEntity.ok(this.checksService.update(checks));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(int id) {
        return ResponseEntity.ok(this.checksService.deleteById(id));
    }

}

