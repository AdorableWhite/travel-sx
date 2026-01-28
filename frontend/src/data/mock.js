export const mockHome = {
  hotAttractions: [
    { id: 1, name: '云冈石窟', city: '大同', level: '5A', intro: '北魏石窟艺术。' },
    { id: 2, name: '平遥古城', city: '晋中', level: '5A', intro: '明清古城。' },
  ],
  hotFoods: [
    { id: 1, name: '刀削面', city: '太原', rating: 4.8 },
    { id: 2, name: '栲栳栳', city: '吕梁', rating: 4.6 },
  ],
  hotTopics: [
    { id: 1, name: '寻找最美古建', description: '探访山西古建筑之美' },
    { id: 2, name: '山西下雪了', description: '冬季雪景与古城风情' },
  ],
  hotNotes: [
    { id: 1, title: '一日游平遥古城', content: '从城墙到票号。' },
    { id: 2, title: '雪后的云冈石窟', content: '石窟在雪中更显庄严。' },
  ],
};

export const mockAttractions = mockHome.hotAttractions;
export const mockFoods = mockHome.hotFoods;
export const mockTopics = mockHome.hotTopics;
export const mockNotes = mockHome.hotNotes;

export const mockItineraries = [
  {
    id: 1,
    title: '大同两日游',
    days: 2,
    status: 'DRAFT',
  },
];

export const mockUser = {
  id: 1,
  nickname: '小晋',
  city: '太原',
};
