 <template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

</script>

<style>

/*全局的父类高度*/
html {
  height: 100%;
}

/*重置body属性*/
body {
  padding: 0;
  margin: 0;
  /*继承html*/
  height: 100vh;
}

/*#app的高度也需要继承*/
#app {
  height: 100%;
}

/*全局链接标签去下划线*/
a {
  text-decoration: none;
}

</style>
