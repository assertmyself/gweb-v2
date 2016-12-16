/**
 **/
PopTree = function (options) {
    this.options = {
        targetValueId: null,
        targetId: null,
        targetType: null,
        onlyLeaf: true,
        setMultiValues: null,
        selectObj: null,
        setting: null,
        title: null,
        callback: null
    };
    this.options = $.extend(this.options, options || {});
    this.init();
    this.show();
};

PopTree.prototype.init = function () {
    this.treeId = "_treeId_" + parseInt(Math.random() * 1000 + 1);
    var pop = this;
    var treeId = this.treeId;
    this.window = art.dialog({
        style: "background-color:#9ACCFB",
        padding: 0,
        content: '<div class="ztree" id="' + treeId + '" style="width:' + (this.options.width || 280) + 'px;height:' + (this.options.height || 320) + 'px;overflow-y:auto;">',
        width: this.options.width || 280,
        height: this.options.height || 320,
        drag: true,
        lock: false,
        scroll: true,
        init: function () {
            pop.initTree(treeId);
        },
    	title: this.options.title ? this.options.title : '消息'
    });
    this.window.button(
        { name: '确定',
            callback: function () {
                if (pop.confirmClick) {
                    pop.confirmClick.call(this, pop.tree.getSelectedNodes());
                    return;
                }
                var selectNodes = pop.tree.getSelectedNodes();
                var checkNodes = pop.tree.getCheckedNodes();
                if (selectNodes.length > 0 && !pop.options.check) {
                    if (pop.options.onlyLeaf && selectNodes[0].isParent) {
                        alert("请选择叶子节点");
                        return false;
                    }
                    pop.setValue(selectNodes[0].name);
                    pop.setId(selectNodes[0].id);
                    pop.setType(selectNodes[0].type);
                } else if (checkNodes.length > 0) {
                    if (pop.options.setMultiValues) {
                        pop.options.setMultiValues.apply(pop, [checkNodes]);
                    }
                    $.each(checkNodes, function () {
                        if (!this.childs) {
                            pop.setMultiValue(this.id, this.name);
                        }
                    })
                }
                if (pop.options.callback) {
                    pop.options.callback(pop);
                }

            }, focus: true},
        {
            name: '关闭',
            callback: function () {
                this.close();
            }
        });

};

PopTree.prototype.initTree = function (treeWrapId) {
    this.setting = {
        view: {fontCss: getFont},
        async: {
            enable: true,
            autoParam: ["id", "uid", "type"],
            url: this.options.url
        }, callback: {
            onAsyncSuccess: onAsyncSuccess
        }
    };

    if (this.options.check) {
        this.setting.check = {
            enable: true,
            chkStyle: "checkbox",
            chkboxType: { "Y": "ps", "N": "ps" }
        }
    }
    if (this.options.setting) {
        var setting = this.options.setting;
        for (var name in setting) {
            this.setting[name] = $.extend(this.setting[name], setting[name]);
        }
    }

    this.initRoot();

    this.tree = $.fn.zTree.init($(selectorAdapt(this.treeId)), this.setting, this.root);

    var execOnce = true;

    var treeObj = this.tree;

    function onAsyncSuccess(event, treeId, treeNode, msg) {
        if (execOnce) {
            var allNodes = treeObj.getNodes();
            if (allNodes && allNodes.length > 0) {
                execOnce = false;
                treeObj.expandNode(treeObj.getNodes()[0], true);
            }
        }
    }
};

PopTree.prototype.initRoot = function () {
    this.root = this.options.root || null;
};

PopTree.prototype.show = function () {
    this.window.show();
};

PopTree.prototype.setValue = function (value) {
    if (this.options.targetValueId) {
        $(selectorAdapt(this.options.targetValueId)).val(value);
    }
};

PopTree.prototype.setId = function (value) {
    if (this.options.targetId) {
        $(selectorAdapt(this.options.targetId)).val(value);
    }
};

PopTree.prototype.setType = function (value) {
    if (this.options.targetType) {
        $(selectorAdapt(this.options.targetType)).val(value);
    }
};

PopTree.prototype.setMultiValue = function (id, value) {
    if (this.options.selectObj) {
        $(selectorAdapt(this.options.selectObj)).append("<option value='" + id + "'>" + value + "</options>");
    } else if (this.options.targetValueId) {
        //为了兼容原来的模式，项目中如果有多选情况必须设selectObj，否则会造成混乱
        $(selectorAdapt(this.options.targetValueId)).append("<option value='" + id + "'>" + value + "</options>");
    }
};

function getFont(treeId, node) {
    return node.font ? node.font : {};
}