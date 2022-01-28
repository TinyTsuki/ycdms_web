package cn.antraces.dms.controller;

import cn.antraces.dms.entity.Attendances;
import cn.antraces.dms.service.AttendancesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Attendances)表控制层
 *
 * @author silver
 * @since 2021-11-16 16:24:32
 */
@RestController
@RequestMapping("api/Attendance")
public class AttendancesController {
    /**
     * 服务对象
     */
    @Resource
    private AttendancesService attendancesService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Attendances> queryById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.attendancesService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param attendances 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Attendances> add(Attendances attendances) {
        return ResponseEntity.ok(this.attendancesService.insert(attendances));
    }

    /**
     * 编辑数据
     *
     * @param attendances 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Attendances> edit(Attendances attendances) {
        return ResponseEntity.ok(this.attendancesService.update(attendances));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(int id) {
        return ResponseEntity.ok(this.attendancesService.deleteById(id));
    }

}

