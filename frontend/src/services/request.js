const DEFAULT_BASE_URL = 'http://localhost:8080';

const getBaseUrl = () => {
  try {
    if (typeof process !== 'undefined' && process.env && process.env.VUE_APP_API_BASE) {
      return process.env.VUE_APP_API_BASE;
    }
  } catch (e) {
    // ignore
  }
  const cached = uni.getStorageSync('apiBase');
  return cached || DEFAULT_BASE_URL;
};

const request = (options) => {
  const baseUrl = getBaseUrl();
  return new Promise((resolve, reject) => {
    uni.request({
      url: `${baseUrl}${options.url}`,
      method: options.method || 'GET',
      data: options.data || {},
      success: (res) => {
        if (res.statusCode >= 200 && res.statusCode < 300) {
          resolve(res.data);
        } else {
          reject(res);
        }
      },
      fail: (err) => reject(err),
    });
  });
};

export default {
  get(url, data) {
    return request({ url, method: 'GET', data });
  },
  post(url, data) {
    return request({ url, method: 'POST', data });
  },
};
