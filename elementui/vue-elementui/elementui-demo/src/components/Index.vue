<template>
  <div id="index">
    Index
  </div>
</template>
<script type="text/javascript">
  export default {
    mounted() {
      this.open();
    },
    methods: {
       open() {
        const h = this.$createElement;

        this.$notify({
          title: '标题名称',
          message: h('i', { style: 'color: teal'}, '欢迎进入程序的世界'),
          offset: 200
        });
      }
    }
  }
</script>