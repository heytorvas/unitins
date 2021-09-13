import matplotlib.pyplot as plt
from image import calculate_distance

def calculate_mm(pixels):
	DPI = 96
	return (pixels * 25.4) / DPI

def set_intervals(palographic_test):
	intervals = []
	intervals_list = []
	for palo in palographic_test:
		if palo.h > palo.w:
			palo.is_interval = True
			intervals.append(palo)
		else:
			intervals_list.append(intervals)
			intervals = []

	return intervals_list

def set_plot_yield(intervals_list):
	palos_per_interval = {}
	for i in range(len(intervals_list)):
		palos_per_interval.update({i+1: len(intervals_list[i])})

	count_intervals = list(palos_per_interval.keys())
	count_intervals_palos = list(palos_per_interval.values())

	plt.plot(count_intervals, count_intervals_palos)
	plt.title('Quantidade de Palos por Intervalo')
	plt.xlabel('Intervalo')
	plt.ylabel('Palos')
	plt.show()

	return palos_per_interval

def get_sum_diff(intervals_list):
	diff_list = []
	for i in range(len(intervals_list)):
		try:
			diff = len(intervals_list[i+1]) - len(intervals_list[i])
			if diff < 0:
				diff = diff * - 1
			diff_list.append(diff)
		except:
			break

	return sum(diff_list)

def calculate_nor(sum_diff, total_palos):
	return (sum_diff * 100) / total_palos

def get_palos_size(intervals_list):
	size_palos = {}
	ma_palo = []
	mi_palo = []
	for interval in range(len(intervals_list)):
		major_palo = 0
		minor_palo = 0
		for palo in intervals_list[interval]:
			if major_palo == 0 and minor_palo == 0:
				major_palo = palo
				minor_palo = palo

			elif palo.h > major_palo.h:
				major_palo = palo

			elif palo.h < minor_palo.h:
				minor_palo = palo
			
			else:
				pass

		ma_palo.append(major_palo)
		mi_palo.append(minor_palo)
		print('intervalo {} - maior: {} | tamanho: {}'.format(interval+1, major_palo.roi, calculate_mm(major_palo.h)))
		print('intervalo {} - menor: {} | tamanho: {}'.format(interval+1, minor_palo.roi, calculate_mm(minor_palo.h)))


	size_palos.update({'major': ma_palo, 'minor': mi_palo})
	return size_palos


def get_size_palos_interval(size_palos):
	sum_major = (sum(map(lambda x: calculate_mm(x.h), size_palos['major']))) / 5
	sum_minor = (sum(map(lambda x: calculate_mm(x.h), size_palos['minor']))) / 5
	return (sum_major + sum_minor) / 2

def get_major_palo_test(size_palos):
	palo = max(size_palos['major'], key=lambda x: calculate_mm(x.h))
	return palo

def get_minor_palo_test(size_palos):
	palo = min(size_palos['minor'], key=lambda x: calculate_mm(x.h))
	return palo

def calculate_size_palos(size_palos):
    major_sum = sum(calculate_mm(palo.h) for palo in size_palos['major'])
    minor_sum = sum(calculate_mm(palo.h) for palo in size_palos['minor'])

    result = ((major_sum/5) + (minor_sum/5)) / 2

    print(f'somatorio major: {major_sum} | E/5: {major_sum/5}')
    print(f'somatorio minor: {minor_sum} | E/5: {minor_sum/5}')

    return result

#TODO:
def get_distance_between_palos(intervals_list):
	for interval in intervals_list:
		count = 0
		initial = interval[0]
		for palo in range(len(interval)):
			if interval[palo].is_last_row == True:
				distance = calculate_distance(initial.cX, initial.cY, interval[palo].cX, interval[palo].cY)
				# print(interval[palo].roi)
				# print('initial: ', initial.__dict__)
				# print('final: ', interval[palo].__dict__)
				# print('distance: ', distance)
				count += distance
				try:
					initial = interval[palo+1]
				except:
					break
				
			if interval[palo] == interval[len(interval)-1]:
				distance = calculate_distance(initial.cX, initial.cY, interval[palo].cX, interval[palo].cY)
				# print(interval[palo].roi)
				# print('initial: ', initial.__dict__)
				# print('final: ', interval[palo].__dict__)
				# print('distance: ', distance)
				count += distance
				

		print('distancia por intervalo: ', calculate_mm(count))
		


		# for palo in interval:
		# 	if palo.is_last_row:
		# 		distance = calculate_distance(initial.cX, initial.cY, palo.cX, palo.cY)
		# 		count = count + distance
				