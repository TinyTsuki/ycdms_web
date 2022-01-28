package cn.antraces.dms.controller;

import cn.antraces.dms.entity.Events;
import cn.antraces.dms.service.EventsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Events)表控制层
 *
 * @author silver
 * @since 2021-11-16 16:24:33
 */
@RestController
@RequestMapping("api/Event")
public class EventsController {
    /**
     * 服务对象
     */
    @Resource
    private EventsService eventsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Events> queryById(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.eventsService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param events 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Events> add(Events events) {
        return ResponseEntity.ok(this.eventsService.insert(events));
    }

    /**
     * 编辑数据
     *
     * @param events 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Events> edit(Events events) {
        return ResponseEntity.ok(this.eventsService.update(events));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(int id) {
        return ResponseEntity.ok(this.eventsService.deleteById(id));
    }

}

