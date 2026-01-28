<template>
  <view class="tag-group">
    <view v-for="(item, index) in tags" :key="index" class="tag">
      {{ item }}
    </view>
  </view>
</template>

<script setup>
defineProps({
  tags: {
    type: Array,
    default: () => [],
  },
});
</script>

<style scoped lang="scss">
.tag-group {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.tag {
  padding: 4px 8px;
  border-radius: 12px;
  background: rgba(154, 46, 46, 0.08);
  color: #9a2e2e;
  font-size: 12px;
}
</style>
