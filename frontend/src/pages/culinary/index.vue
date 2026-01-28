<template>
  <view class="container">
    <SectionTitle title="晋善晋美" extra="面食百科" />
    <InfoCard v-for="item in foods" :key="item.id" :title="item.name" :subtitle="item.city">
      <view class="card-desc">评分 {{ item.rating || '--' }}</view>
    </InfoCard>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchFoods } from '../../services/api';
import { mockFoods } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const foods = ref(mockFoods);

const load = async () => {
  try {
    const res = await fetchFoods(10);
    if (res && res.data) {
      foods.value = res.data;
    }
  } catch (e) {
    foods.value = mockFoods;
  }
};

onMounted(load);
</script>

<style scoped lang="scss">
.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
<template>
  <view class="container">
    <SectionTitle title="晋善晋美" extra="面食百科" />
    <InfoCard v-for="item in foods" :key="item.id" :title="item.name" :subtitle="item.city">
      <view class="card-desc">评分 {{ item.rating || '--' }}</view>
    </InfoCard>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchFoods } from '../../services/api';
import { mockFoods } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const foods = ref(mockFoods);

const load = async () => {
  try {
    const res = await fetchFoods(10);
    if (res && res.data) {
      foods.value = res.data;
    }
  } catch (e) {
    foods.value = mockFoods;
  }
};

onMounted(load);
</script>

<style scoped lang="scss">
.card-desc {
  font-size: 12px;
  color: #7a6f64;
}
</style>
