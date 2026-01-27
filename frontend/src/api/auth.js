import request from '../utils/request'

export function getCaptcha() {
  return request({
    url: '/auth/captcha',
    method: 'get'
  })
}

export function sendSms(phone) {
  return request({
    url: '/auth/send-sms',
    method: 'post',
    data: { phone }
  })
}

export function register(data) {
  return request({
    url: '/auth/register',
    method: 'post',
    data
  })
}

export function loginByPassword(data) {
  return request({
    url: '/auth/login/password',
    method: 'post',
    data
  })
}

export function loginBySms(data) {
  return request({
    url: '/auth/login/sms',
    method: 'post',
    data
  })
}
