<template>
  <div class="login-container">
    <el-card class="login-card">
      <template #header>
        <div class="card-header">
          <span>Travel SX - Login</span>
        </div>
      </template>
      
      <el-tabs v-model="activeTab">
        <!-- Tab 1: Password Login -->
        <el-tab-pane label="Password Login" name="password">
          <el-form :model="passForm" ref="passFormRef" :rules="passRules">
            <el-form-item prop="account">
              <el-input v-model="passForm.account" placeholder="Username / Phone">
                <template #prefix><el-icon><User /></el-icon></template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="passForm.password" type="password" placeholder="Password" show-password>
                <template #prefix><el-icon><Lock /></el-icon></template>
              </el-input>
            </el-form-item>
            <el-form-item prop="captchaCode">
              <div class="captcha-row">
                <el-input v-model="passForm.captchaCode" placeholder="Captcha" style="width: 60%">
                  <template #prefix><el-icon><Picture /></el-icon></template>
                </el-input>
                <img :src="captchaImage" @click="refreshCaptcha" class="captcha-img" />
              </div>
            </el-form-item>
            <el-button type="primary" class="full-btn" @click="handlePassLogin">Login</el-button>
          </el-form>
        </el-tab-pane>

        <!-- Tab 2: SMS Login -->
        <el-tab-pane label="SMS Login" name="sms">
          <el-form :model="smsForm" ref="smsFormRef" :rules="smsRules">
            <el-form-item prop="phone">
              <el-input v-model="smsForm.phone" placeholder="Phone Number">
                <template #prefix><el-icon><Iphone /></el-icon></template>
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <div class="code-row">
                 <el-input v-model="smsForm.code" placeholder="SMS Code" style="width: 60%">
                   <template #prefix><el-icon><Message /></el-icon></template>
                 </el-input>
                 <el-button :disabled="countdown > 0" @click="handleSendSms" style="width: 35%">
                   {{ countdown > 0 ? `${countdown}s` : 'Send Code' }}
                 </el-button>
              </div>
            </el-form-item>
             <el-button type="primary" class="full-btn" @click="handleSmsLogin">Login</el-button>
          </el-form>
        </el-tab-pane>

        <!-- Tab 3: Register -->
        <el-tab-pane label="Register" name="register">
           <el-form :model="regForm" ref="regFormRef" :rules="regRules">
            <el-form-item prop="phone">
              <el-input v-model="regForm.phone" placeholder="Phone Number">
                <template #prefix><el-icon><Iphone /></el-icon></template>
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <div class="code-row">
                 <el-input v-model="regForm.code" placeholder="SMS Code" style="width: 60%">
                   <template #prefix><el-icon><Message /></el-icon></template>
                 </el-input>
                 <el-button :disabled="regCountdown > 0" @click="handleRegSendSms" style="width: 35%">
                   {{ regCountdown > 0 ? `${regCountdown}s` : 'Send Code' }}
                 </el-button>
              </div>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="regForm.password" type="password" placeholder="Set Password" show-password>
                <template #prefix><el-icon><Lock /></el-icon></template>
              </el-input>
            </el-form-item>
             <el-button type="success" class="full-btn" @click="handleRegister">Register</el-button>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { User, Lock, Picture, Iphone, Message } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { getCaptcha, sendSms, register, loginByPassword, loginBySms } from '../api/auth'

const router = useRouter()
const activeTab = ref('password')

// Password Login Data
const passForm = ref({ account: '', password: '', captchaCode: '', captchaKey: '' })
const captchaImage = ref('')
const passFormRef = ref(null)
const passRules = {
  account: [{ required: true, message: 'Please input account', trigger: 'blur' }],
  password: [{ required: true, message: 'Please input password', trigger: 'blur' }],
  captchaCode: [{ required: true, message: 'Please input captcha', trigger: 'blur' }]
}

// SMS Login Data
const smsForm = ref({ phone: '', code: '' })
const smsFormRef = ref(null)
const countdown = ref(0)
const smsRules = {
  phone: [{ required: true, message: 'Please input phone', trigger: 'blur' }],
  code: [{ required: true, message: 'Please input code', trigger: 'blur' }]
}

// Register Data
const regForm = ref({ phone: '', code: '', password: '' })
const regFormRef = ref(null)
const regCountdown = ref(0)
const regRules = {
  phone: [{ required: true, message: 'Please input phone', trigger: 'blur' }],
  code: [{ required: true, message: 'Please input code', trigger: 'blur' }],
  password: [{ required: true, message: 'Please set password', trigger: 'blur' }]
}

// Methods
const refreshCaptcha = async () => {
  const res = await getCaptcha()
  passForm.value.captchaKey = res.key
  captchaImage.value = res.image
}

const handlePassLogin = async () => {
  if (!passFormRef.value) return
  await passFormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const token = await loginByPassword(passForm.value)
        localStorage.setItem('token', token)
        ElMessage.success('Login Success')
        router.push('/home')
      } catch (e) {
        refreshCaptcha()
      }
    }
  })
}

const handleSendSms = async () => {
  if (!smsForm.value.phone) {
    ElMessage.warning('Please input phone number')
    return
  }
  await sendSms(smsForm.value.phone)
  ElMessage.success('Code sent (Check console/logs)')
  countdown.value = 60
  const timer = setInterval(() => {
    countdown.value--
    if (countdown.value <= 0) clearInterval(timer)
  }, 1000)
}

const handleSmsLogin = async () => {
   if (!smsFormRef.value) return
   await smsFormRef.value.validate(async (valid) => {
     if (valid) {
       const token = await loginBySms(smsForm.value)
       localStorage.setItem('token', token)
       ElMessage.success('Login Success')
       router.push('/home')
     }
   })
}

const handleRegSendSms = async () => {
  if (!regForm.value.phone) {
    ElMessage.warning('Please input phone number')
    return
  }
  await sendSms(regForm.value.phone)
  ElMessage.success('Code sent (Check console/logs)')
  regCountdown.value = 60
  const timer = setInterval(() => {
    regCountdown.value--
    if (regCountdown.value <= 0) clearInterval(timer)
  }, 1000)
}

const handleRegister = async () => {
  if (!regFormRef.value) return
  await regFormRef.value.validate(async (valid) => {
    if (valid) {
      await register(regForm.value)
      ElMessage.success('Register Success, Please Login')
      activeTab.value = 'password'
    }
  })
}

onMounted(() => {
  refreshCaptcha()
})
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}
.login-card {
  width: 400px;
}
.captcha-row, .code-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}
.captcha-img {
  cursor: pointer;
  height: 32px;
}
.full-btn {
  width: 100%;
}
</style>
