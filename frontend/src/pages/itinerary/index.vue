<template>
  <view class="container">
    <SectionTitle title="我的行程" extra="PC端拖拽编排" />
    <InfoCard v-for="item in itineraries" :key="item.id" :title="item.title" :subtitle="`共 ${item.days} 天`" @click="showDetail(item.id)" />

    <view v-if="detail && detail.itinerary" class="section">
      <SectionTitle title="行程详情" />
      <InfoCard :title="detail.itinerary.title" :subtitle="detail.itinerary.status" />
      <view v-for="day in detail.days || []" :key="day.day.id" class="day-block">
        <view class="day-title">第 {{ day.day.dayIndex }} 天 · {{ day.day.summary }}</view>
        <view v-for="item in day.items" :key="item.id" class="day-item">
          景点 {{ item.attractionId }} · {{ item.startTime || '' }} · {{ item.durationMinutes || 0 }} 分钟
        </view>
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { fetchItineraries, fetchItineraryDetail } from '../../services/api';
import { mockItineraries } from '../../data/mock';
import SectionTitle from '../../components/SectionTitle.vue';
import InfoCard from '../../components/InfoCard.vue';

const itineraries = ref(mockItineraries);
const detail = ref(null);

const load = async () => {
  try {
    const res = await fetchItineraries(1);
    if (res && res.data) {
      itineraries.value = res.data;
    }
  } catch (e) {
    itineraries.value = mockItineraries;
  }
};

const showDetail = async (id) => {
  try {
    const res = await fetchItineraryDetail(id);
    if (res && res.data) {
      detail.value = res.data;
    }
  } catch (e) {
    detail.value = null;
  }
};

onMounted(load);
</script>

<style scoped lang="scss">
.day-block {
  margin-top: 12px;
  padding: 12px;
  background: #fff;
  border-radius: 8px;
}

.day-title {
  font-weight: 600;
  margin-bottom: 6px;
}

.day-item {
  font-size: 12px;
  color: #7a6f64;
  margin-bottom: 4px;
}
</style>
