import {useEffect, useState} from "react";

export { ImagePosUtil }
function ImagePosUtil({imgLink}: {imgLink: string}) {
    const [imgHeight, setImgHeight] = useState(0);
    const [imgWidth, setImgWidth] = useState(0);
    // const [loading, setLoading] = useState<boolean>();
    useEffect(() => {
        if (!imgLink) return;
        // setLoading(true);
        const image = new Image();
        image.onload = () => {
            setImgWidth(image.naturalWidth);
            setImgHeight(image.naturalHeight);
            // setLoading(false);
        }
        image.onerror = () => {
            console.error("Error loading image", imgLink);
            // setLoading(false);
        }

        image.src = imgLink;
    }, [imgLink]);

    const [selectedArray, setSelectedArray] = useState<[number, number, number, number][]>([]);
    const pushToSelectedArray = (x: number, y: number, w: number, h: number) => {
        setSelectedArray(() => [...selectedArray, [x, y, w, h]]);
    }



    const container = document.getElementById('image-container');
    const box = document.getElementById('selection-box');
    const result = document.getElementById('coordinates');

    let isDrawing = false;
    let startX = 0;
    let startY = 0;

    if (container && box && result) {
// 1. Khi nhấn chuột xuống: Bắt đầu vẽ
        container.addEventListener('mousedown', (e) => {
            isDrawing = true;

            // Lấy vị trí của khung chứa ảnh so với viewport
            const rect = container.getBoundingClientRect();

            // Tính tọa độ điểm bắt đầu tương đối với ảnh
            startX = e.clientX - rect.left;
            startY = e.clientY - rect.top;

            // Đặt vị trí ban đầu cho khung chọn
            box.style.left = startX  + 'px';
            box.style.top = startY + 'px';
            box.style.width = '0px';
            box.style.height = '0px';
            box.style.display = 'block';
        });

// 2. Khi di chuyển chuột: Cập nhật kích thước hình chữ nhật
        container.addEventListener('mousemove', (e) => {
            if (!isDrawing) return;

            const rect = container.getBoundingClientRect();
            const currentX = e.clientX - rect.left;
            const currentY = e.clientY - rect.top;

            // Tính toán kích thước và xử lý trường hợp kéo ngược chuột (lên trên hoặc sang trái)
            const width = Math.abs(currentX - startX);
            const height = Math.abs(currentY - startY);
            const left = Math.min(currentX, startX);
            const top = Math.min(currentY, startY);

            box.style.left = left + 'px';
            box.style.top = top + 'px';
            box.style.width = width + 'px';
            box.style.height = height + 'px';

            // Hiển thị tọa độ thời gian thực (Tùy chọn)
            printCoordinates(left, top, width, height);
        });

// 3. Khi thả chuột ra: Kết thúc vẽ và lưu tọa độ
        window.addEventListener('mouseup', () => {
            if (isDrawing) {
                isDrawing = false;

                // Lấy tọa độ cuối cùng để xử lý logic của bạn
                const finalLeft = parseInt(box.style.left);
                const finalTop = parseInt(box.style.top);
                const finalWidth = parseInt(box.style.width);
                const finalHeight = parseInt(box.style.height);

                printCoordinates(finalLeft, finalTop, finalWidth, finalHeight);
                pushToSelectedArray(finalLeft, finalTop, finalWidth, finalHeight);
            }
        });
    }

    const highlight = (input: [number, number, number, number]) => {
        if (box) {
            box.style.left = input[0] + 'px';
            box.style.top = input[1] + 'px';
            box.style.width = input[2] + 'px';
            box.style.height = input[3] + 'px';
            printCoordinates(input[0], input[1], input[2], input[3]);

        }
    };
    const deleteFromSelectedArray = (i: number) => {
        selectedArray.splice(i, 1);
        setSelectedArray(() => [...selectedArray]);
        if (box) {
            box.style.left = '0px';
            box.style.top = '0px';
            box.style.width = '0px';
            box.style.height = '0px';
        }
    };

    const copyToClipboard = (input: [number, number, number,  number]) => {
        navigator.clipboard.writeText(`${input[0]}\t${input[1]}\t${input[2]}\t${input[3]}`).then();
    };

    // Hàm hiển thị tọa độ ra màn hình
    const printCoordinates = (x: number, y: number, w: number, h: number)=> {
        if (result) {
            result.innerHTML = `
            <div
                style={{
                    display: 'block',
                }}
            >
                <strong>Góc trên - trái (X, Y):</strong> (${x}px, ${y}px) <br>
                <strong>Kích thước (Rộng x Cao):</strong> ${w}px x ${h}px <br>
                <strong>Góc dưới - phải (X, Y):</strong> (${x + w}px, ${y + h}px)            
            </div>
            `;
        }
    };

    return (
        <>
            <div id="image-container"
                 style={{ position: 'relative', display: 'inline-block', userSelect: 'none' }}
                 onMouseDown={() => window.dispatchEvent(new Event('mousedown'))}
                 onMouseMove={() => window.dispatchEvent(new Event('mousemove'))}
                 onMouseUp={() => window.dispatchEvent(new Event('mouseup'))}
            >
                <div id="selection-box"
                     style={{ border: '2px dashed #007bff', backgroundColor: 'rgba(0, 123, 255, 0.2)', position: 'absolute', display: 'none', pointerEvents: 'none' }}

                ></div>
                <img
                    src={imgLink}
                    draggable="false" // Ngăn chặn hành động kéo thả mặc định của trình duyệt
                    style={{
                        display: 'block',
                        width: imgWidth,
                        height: imgHeight,
                        userSelect: 'none', // Ngăn bôi đen ảnh
                        WebkitUserDrag: 'none' // Thuộc tính bổ trợ cho một số trình duyệt cũ
                    }}
                 alt="Không có ảnh"/>
            </div>
            <div
                style={{
                    display: 'flex',
                    justifyContent: 'stretch',
                }}
            >
                <div id="coordinates" style={{ marginTop: '10px', fontFamily: 'monospace', flex: 1, }}>
                    Tọa độ: Bấm và kéo chuột trên ảnh để chọn vùng.
                </div>
                <div
                    style={{
                        flex: 1,
                        height: '200px',
                        overflowY: 'auto',
                    }}
                >
                    {selectedArray.map((item, i) => (
                        <div key={i}
                             style={{
                                 border: '1px dashed #000000',
                             }}

                        >
                            <button
                                onClick={() => {
                                    highlight(item);
                                    // window.alert("clicked");
                                }}
                            >{item[0]} {item[1]}, {item[2]} x {item[3]}</button>
                            <button onClick={() => {copyToClipboard(item)}}>Copy</button>
                            <button
                                onClick={() => {
                                    deleteFromSelectedArray(i);
                                }}
                            >x</button>
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
}

