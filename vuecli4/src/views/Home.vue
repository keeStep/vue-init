<template>
  <div>
    <el-container>
      <el-header class="homeHeader">
        <div class="title">微人事</div>
        <el-dropdown class="userInfo" @command="commandHandler">
          <span class="el-dropdown-link">
            {{user.name}}
            <i><img :src="user.userface" alt=""/></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
            <el-dropdown-item command="setting">设置</el-dropdown-item>
            <el-dropdown-item divided command="logout">注销登录</el-dropdown-item>
            <!-- <el-dropdown-item disabled>双皮奶</el-dropdown-item> -->
            <!-- <el-dropdown-item divided>蚵仔煎</el-dropdown-item> 分割线 -->
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-main>Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      user: JSON.parse(window.sessionStorage.getItem("user"))
    }
  },
  methods: {
    commandHandler(cmd) {
      if (cmd == 'logout') {
        this.$confirm('此操作将注销登录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.getRequest("/logout");
          // 清空登录数据
          window.sessionStorage.removeItem("user");
          this.$router.replace("/");
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });          
        });
      }
    }
  }
};
</script>

<style>
.homeHeader {
  background-color: deepskyblue;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0px 15px;
  box-sizing: border-box;
}

.homeHeader.title {
  font-size: 30px;
  font-family: "Franklin Gothic Medium";
}

.homeHeader.userInfo {
  cursor: pointer;
}

.el-dropdown-link img {
  width: 48px;
  height: 48px;
  border-radius: 24px;
  margin-left: 8px;
}

.el-dropdown-link {
  display: flex;
  align-items: center;
}
</style>