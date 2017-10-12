<template>
    <div class="sidebar">
        <el-menu :default-active="onRoutes" class="el-menu-vertical-demo" theme="light" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index">
                        <template slot="title"><i :class="item.icon"></i>{{ item.title }}</template>
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">{{ subItem.title }}</el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index">
                        <i :class="item.icon"></i>{{ item.title }}
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                items: [
                    // {
                    //     icon: 'el-icon-setting',
                    //     index: '/home',
                    //     title: '首页'
                    // },
                    // {
                    //     icon: 'el-icon-menu',
                    //     index: '2',
                    //     title: '用户管理',
                    //     subs: [
                    //         {
                    //             index: '/users',
                    //             title: '用户列表'
                    //         }
                    //     ]
                    // }
                ]
            }
        },
        beforeMount() {
            var meuns = sessionStorage.getItem('meuns');
            var qs = require('qs');
            this.items = qs.parse(meuns);
        },
        computed: {
            onRoutes(){
                console.log(this.$route.path);
                return this.$route.path;
            }
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        width: 200px;
        left: 0;
        top: 70px;
        bottom:0;
        background: #2E363F;
    }
    .sidebar > ul {
        height:100%;
    }
</style>