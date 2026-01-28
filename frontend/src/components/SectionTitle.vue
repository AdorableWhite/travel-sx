<template>
  <view class="section-title">
    <text>{{ title }}</text>
    <text v-if="extra" class="extra">{{ extra }}</text>
  </view>
</template>

<script setup>
defineProps({
  title: {
    type: String,
    default: '',
  },
  extra: {
    type: String,
    default: '',
  },
});
</script>

<style scoped lang="scss">
.section-title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: 600;
  color: #9a2e2e;
  margin-bottom: 8px;
}

.extra {
  font-size: 12px;
  color: #7a6f64;
}
</style>
