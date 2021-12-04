//
//  FrameworkCell.swift
//  frameworks
//
//  Created by APPS2T on 22/11/21.
//

import UIKit

class FrameworkCell: UITableViewCell {
    
    var framework: Framework? {
        didSet { renderUI() }
    }
    
    @IBOutlet var nameLabel: UILabel!
    @IBOutlet var imageNameLabel: UIImageView!
    
    override func layoutSubviews() {
        super.layoutSubviews()
    }
    
    private func renderUI()  {
        guard let framework = framework else { return }
        nameLabel.text = framework.name
        imageNameLabel.image = UIImage(named: framework.imageName)
    }
}

