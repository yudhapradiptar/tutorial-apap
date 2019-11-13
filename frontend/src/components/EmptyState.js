import React from "react";

export default function EmptytState(props) {
    const { item, onChange } = props;
    const { nama, deskripsi, harga, checked } = item;

    const handleChange = () =>
        !!onChange && onChange({ ...item, checked: !checked });

    return (
        <div>
            <h4>Belum ada Menu Favorit</h4>
            <p>Silahkan Pilih menu yang ada disamping</p>
        </div>
    );
}