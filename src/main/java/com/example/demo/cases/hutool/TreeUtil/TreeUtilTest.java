package com.example.demo.cases.hutool.TreeUtil;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeUtilTest {

    public static void main(String[] args) {
        //模拟的从数据库查询出来的数据
        List<PfHospitalInfo> fileCategoryDOS = getData();
        List<TreeNode<String>> collect = fileCategoryDOS.stream().map(fileCategoryDO -> {
            Map<String, Object> map = new HashMap<>();
            //map.put("level", 1);层级
            //map.put("gmt_create", LocalDateTime.now());添加额外数据
            //map.put("gmt_modifier", LocalDateTime.now());
            TreeNode<String> treeNode = new TreeNode<String>().setId(fileCategoryDO.getMdid())
                    .setName(fileCategoryDO.getHospName())
                    .setParentId(fileCategoryDO.getParentId())
                    //.setWeight(fileCategoryDO.getSort())
                    .setExtra(map);
            return treeNode;
        }).collect(Collectors.toList());

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setIdKey("mdid");
        treeNodeConfig.setChildrenKey("childrenNode");
        // 最大递归深度
        treeNodeConfig.setDeep(5);

        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(collect, "7bac2803-0cf5-4df1-b57d-d110e9f1c8a3", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    // 扩展属性 ...
                    //tree.putExtra("level", treeNode.getExtra().getOrDefault("level", 2));
                    //tree.putExtra("gmt_create", treeNode.getExtra().getOrDefault("gmt_create", null));
                });

        System.out.println(JSON.toJSONString(treeNodes));
    }

    private static List<PfHospitalInfo> getData() {
        return Lists.newArrayList(new PfHospitalInfo("3570da9f-5646-431e-982b-7ad51239289e", "7bac2803-0cf5-4df1-b57d-d110e9f1c8a3", "zhe1", 1),
                new PfHospitalInfo("2cf51504-3d5f-4564-8b10-a70a87aa8a68", "3570da9f-5646-431e-982b-7ad51239289e", "zhe2", 10),
                new PfHospitalInfo("54afe85a-28e2-4025-aa18-ff64735b2163", "3570da9f-5646-431e-982b-7ad51239289e", "zhe3", 100),
                new PfHospitalInfo("2f8047c8-27e0-4b83-b38e-58e2df3b00d1", "54afe85a-28e2-4025-aa18-ff64735b2163", "zhe3", 100));
    }

    private static List<PfHospitalInfo> getData2() {
        return Lists.newArrayList(new PfHospitalInfo("1", "0", "zhe1", 1),
                new PfHospitalInfo("2", "1", "zhe2", 10),
                new PfHospitalInfo("3", "1", "zhe3", 100),
                new PfHospitalInfo("4", "2", "zhe4", 101));
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class PfHospitalInfo{

    private String mdid;

    private String parentId;

    private String hospName;

    private Integer sort;
}

