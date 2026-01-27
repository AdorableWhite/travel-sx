<template>
  <div class="home-container">
    <h1>Welcome to Travel SX</h1>
    <el-button type="danger" @click="logout">Logout</el-button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const logout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
.home-container {
  padding: 50px;
  text-align: center;
}
</style>
