<template>
  <view class="container">
    <view class="search-bar">
      <input v-model="keyword" class="input" placeholder="搜索景点 / 美食 / 游记" @confirm="handleSearch" />
      <button class="btn" @click="handleSearch">搜索</button>
    </view>

    <view v-if="loading" class="tip">搜索中...</view>

    <view v-else>
      <view class="section">
        <SectionTitle title="景点" />
        <InfoCard v-for="item in result.attractions" :key="item.id" :title="item.name" :subtitle="item.city" />
      </view>

      <view class="section">
        <SectionTitle title="美食" />
        <InfoCard v-for="item in result.foods" :key="item.id" :title="item.name" :subtitle="item.city" />
      </view>

      <view class="section">
        <SectionTitle title="游记" />
        <InfoCard v-for="item in result.notes" :key="item.id" :title="item.title">
          <view class="card-desc">{{ item.content }}</view>
        </InfoCard>
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue';
import { searchAll } from '../../services/api';
import { mockHome } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const keyword = ref('');
const loading = ref(false);
const result = ref({
  attractions: mockHome.hotAttractions,
  foods: mockHome.hotFoods,
  notes: mockHome.hotNotes,
});

const handleSearch = async () => {
  if (!keyword.value) {
    uni.showToast({ title: '请输入关键词', icon: 'none' });
    return;
  }
  loading.value = true;
  try {
    const res = await searchAll(keyword.value, 6);
    if (res && res.data) {
      result.value = res.data;
    }
  } catch (e) {
    result.value = {
      attractions: mockHome.hotAttractions,
      foods: mockHome.hotFoods,
      notes: mockHome.hotNotes,
    };
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped lang="scss">
.search-bar {
  display: flex;
  gap: 8px;
  margin-bottom: 12px;
}

.input {
  flex: 1;
  padding: 8px;
  border-radius: 8px;
  background: #fff;
  font-size: 14px;
}

.btn {
  background: #9a2e2e;
  color: #fff;
  border-radius: 8px;
  padding: 0 12px;
}

.tip {
  text-align: center;
  color: #7a6f64;
  padding: 20px 0;
}

.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
