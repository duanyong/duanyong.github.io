package me.duanyong.blog.action;


import com.google.gson.Gson;
import me.duanyong.blog.bean.Department;
import me.duanyong.blog.bean.UITree.TreeNode;
import me.duanyong.blog.bean.User;
import me.duanyong.blog.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duanyong on 9/14/16.
 */

@RestController
@RequestMapping("/depart")
public class DepartmentController extends BaseRestAction {
    private static final Logger log = LogManager.getLogger();

    @Autowired
    private WebApplicationContext context;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<TreeNode>> list() {
        List<Department> list = context.getBean(DepartmentService.class).find();

        if (list == null) {
            list = new ArrayList<>();
        }

        List<TreeNode> nodes = new ArrayList<>();
        TreeNode node;
        for (Department depart : list) {
            node = new TreeNode();
            node.id         = depart.getId().toString();
            node.text       = depart.getName();
            node.parent     = "#";
            node.children   = true;


            nodes.add(node);
        }

       return new ResponseEntity<List<TreeNode>>(nodes, HttpStatus.OK);
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<TreeNode>> list(@RequestParam(value = "depart_id") Integer departId) {
        List<User> list = context.getBean(DepartmentService.class).listByDepartmentId(departId);
        if (list == null) {
            list = new ArrayList<>();
        }

        List<TreeNode> nodes = new ArrayList<>();
        TreeNode node;
        for (User user : list) {
            node = new TreeNode();
            node.id         = "user" + user.getId().toString();
            node.text       = user.getFirstname() + user.getLastname();
            node.children   = false;
//            node.parent     = departId.toString();

            nodes.add(node);
        }

        return new ResponseEntity<List<TreeNode>>(nodes, HttpStatus.OK);
    }
}
