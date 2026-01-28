<template>
  <view class="card" @click="emit('click')">
    <view class="card-title">{{ title }}</view>
    <view class="card-body">
      <slot />
    </view>
    <view v-if="subtitle" class="card-sub">{{ subtitle }}</view>
  </view>
</template>

<script setup>
const emit = defineEmits(['click']);

defineProps({
  title: {
    type: String,
    default: '',
  },
  subtitle: {
    type: String,
    default: '',
  },
});
</script>

<style scoped lang="scss">
.card {
  padding: 12px;
  background: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  margin-bottom: 12px;
}

.card-title {
  font-size: 15px;
  font-weight: 600;
  color: #2b2b2b;
  margin-bottom: 4px;
}

.card-sub {
  margin-top: 6px;
  font-size: 12px;
  color: #7a6f64;
}
</style>
